package org.metranet.keycloak.otp.provider;

import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.authenticators.browser.AbstractUsernameFormAuthenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.metranet.keycloak.otp.util.OtpSmsConstant;
import org.metranet.keycloak.otp.util.OtpSmsSender;
import org.metranet.keycloak.otp.util.RandomStringUtil;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * OtpSmsFormAuthenticator digunakan untuk override Login Action dan Authentication Process.
 * 
 * @see AbstractUsernameFormAuthenticator
 * @author rio.bastian
 */
public class OtpSmsFormAuthenticator extends AbstractUsernameFormAuthenticator {

    Logger logger = Logger.getLogger(OtpSmsFormAuthenticator.class);
    
    /**
     * Get Mobile Phone Number from Input Form
     * @param user
     * @return
     */
    private String getMobileNumber(AuthenticationFlowContext context){
        MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();
        String number = formData.getFirst(OtpSmsConstant.ATTR_PHONE_NUMBER);
        if(null == number) {
            return "";
        }
        return number;
    }
    
    /**
     * Get User By Mobile Phone Number
     * @return
     */
    private UserModel getUserByMobileNumber(AuthenticationFlowContext context, String mobilePhone) {
        List<UserModel> listuser = context.getSession().users().searchForUserByUserAttribute(
                                        OtpSmsConstant.ATTR_PHONE_NUMBER_ADMIN, mobilePhone, context.getRealm());
        if(null != listuser && !listuser.isEmpty() && listuser.size() == 1) {
            return listuser.get(0);
        }
        return null;
    }
    
    /**
     * Generate 6 Random Digit
     * @return
     */
    private String getRandomDigit() {
        RandomStringUtil random = new RandomStringUtil(6, RandomStringUtil.NUMERIC);
        return random.nextString();
    }
    
    /**
     * Prepare OTP to be sent
     * @param context
     */
    private void sendOtp(AuthenticationFlowContext context) {
        String mobile  = getMobileNumber(context);
        UserModel user = getUserByMobileNumber(context, mobile); 
        if(null == user) {
            goErrorPage(context, "Oops, Member not found.");
            return;
        }
        
        // Generate Random Digit
        String key = getRandomDigit();
        
        // Put the data into session, to be compared
        context.getAuthenticationSession().setAuthNote(OtpSmsConstant.SESSION_OTP_CODE, key);

        // Send the key into the User Mobile Phone
        if(OtpSmsSender.sendSMS(mobile, key) == 0) {
            context.setUser(user);
            goPage(context, OtpSmsConstant.PAGE_INPUT_OTP);
            return;
        } else {
            goErrorPage(context, "Failed to send OTP Code.");
            return;
        }
    }
    
    /**
     * Generate Page
     * @param context
     * @param page
     */
    private void goPage(AuthenticationFlowContext context, String page) {
        context.challenge(context.form().createForm(page));
    }
    
    /**
     * Generate Error Page
     * @param context
     * @param message
     */
    private void goErrorPage(AuthenticationFlowContext context, String message) {
        Response challenge = context.form()
                .setError(message)
                .createForm(OtpSmsConstant.PAGE_ERROR);
        context.failureChallenge(AuthenticationFlowError.INTERNAL_ERROR, challenge);
    }
    
    /**
     * Get Attribute Value from a HTML Form
     * @param context
     * @param key
     * @return
     */
    private String getValue(AuthenticationFlowContext context, String key) {
        MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();
        String value = formData.getFirst(key);
        if(null == value) {
            value = "";
        }
        return value;
    }
    
    @Override
    public void action(AuthenticationFlowContext context) {
        switch(getValue(context, OtpSmsConstant.FLAG_PAGE)) {
            case OtpSmsConstant.FLAG_OTP_PAGE :
                authenticate(context);
                break;
            case OtpSmsConstant.FLAG_LOGIN_PAGE :
                sendOtp(context);
                break;
            default :
                goPage(context, OtpSmsConstant.PAGE_INPUT_PHONE_NUMBER);
                break;
        }
    }

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        String sessionKey = context.getAuthenticationSession().getAuthNote(OtpSmsConstant.SESSION_OTP_CODE);
        if (sessionKey != null) {
            // Get OTP from User Input
            String otp = getValue(context, OtpSmsConstant.ATTR_OTP_USER);
            // Validate OTP
            if (otp != null) {
                if (otp.equals(sessionKey)) {
                    context.success();
                } else {
                    goErrorPage(context, "Invalid OTP Code.");
                }
            } else {
                goPage(context, OtpSmsConstant.PAGE_INPUT_OTP);
            }
        } else {
            goPage(context, OtpSmsConstant.PAGE_INPUT_PHONE_NUMBER);
        }
    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
    }

    @Override
    public void close() {
    }

}
