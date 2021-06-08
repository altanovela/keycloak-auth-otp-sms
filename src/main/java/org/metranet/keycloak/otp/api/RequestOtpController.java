package org.metranet.keycloak.otp.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.metranet.keycloak.otp.util.OtpSmsConstant;
import org.metranet.keycloak.otp.util.OtpSmsSender;
import org.metranet.keycloak.otp.util.OtpUtil;
import org.metranet.keycloak.otp.util.RandomStringUtil;

public class RequestOtpController {

    Logger logger = Logger.getLogger(RequestOtpController.class);
    
    private String getRandomDigit() {
        RandomStringUtil random = new RandomStringUtil(6, RandomStringUtil.NUMERIC);
        return random.nextString();
    }

    @GET
    @Path("ck/{k}/{c}")
    public Response verifyOtp(@PathParam("k") String key, @PathParam("c") String code) {
        if(null != code && !code.isEmpty() && null != key && !key.isEmpty()) {
            String codesession = OtpUtil.getCode(OtpSmsConstant.SESSION_OTP_CODE + key);
            if(code.equals(codesession)) {
                return Response.ok(true).build();
            }
        }
        return Response.ok(false).build();
    }

    @GET
    @Path("{m}")
    public Response requestOtp(@PathParam("m") String mobileNumber) {
        // Generate Random Digit
        String key = getRandomDigit();
        
        // Put the data into session, to be compared
        String flag = OtpSmsConstant.SESSION_OTP_CODE + mobileNumber;
        OtpUtil.addCode(flag, key);
        
        // Send SMS
        OtpSmsSender.sendSMS(mobileNumber, key);
        
        return Response.ok(true).build();
    }
}
