package org.metranet.keycloak.otp.util;

import org.jboss.logging.Logger;

public class OtpSmsSender {

    static Logger logger = Logger.getLogger(OtpSmsSender.class);
    
    public static int sendSMS(String phone, String code) {
        logger.error("Send OTP Code [" + code + "] to Phone Number [" + phone + "]");
        return 0;
    }
}
