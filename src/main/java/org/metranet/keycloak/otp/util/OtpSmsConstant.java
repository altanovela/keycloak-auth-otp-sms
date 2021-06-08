package org.metranet.keycloak.otp.util;

/**
 * @author rio.bastian
 */
public class OtpSmsConstant {

    public static final String HTTP_AUTH_HOST              = "http://localhost:8095";
    public static final String ATTR_PHONE_NUMBER_ADMIN     = "mobile_number";
    public static final String ATTR_PHONE_NUMBER           = "user.attributes." + ATTR_PHONE_NUMBER_ADMIN;
    public static final String ATTR_OTP_CODE               = "user.attributes.otp";
    public static final String ATTR_OTP_USER               = "sms_otp_user";
    public static final String FLAG_LOGIN_PAGE             = "login_page";
    public static final String FLAG_OTP_PAGE               = "sms_otp_page";
    public static final String FLAG_PAGE                   = "page_type";

    public static final String SESSION_OTP_CODE            = "otp-code";

    public static final String PAGE_INPUT_PHONE_NUMBER     = "sms-input-phone-number.ftl";
    public static final String PAGE_INPUT_OTP              = "sms-input-otp.ftl";
    public static final String PAGE_ERROR                  = "sms-error.ftl";
}
