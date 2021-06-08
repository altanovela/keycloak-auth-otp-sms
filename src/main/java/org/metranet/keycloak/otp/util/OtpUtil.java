package org.metranet.keycloak.otp.util;

import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.Logger;

public class OtpUtil {

    private static Map<String, String> stock;
    
    static Logger logger = Logger.getLogger(OtpUtil.class);
    
    public static String getCode(String key) {
        if(null == stock) {
            stock = new HashMap<String, String>();
        }
        return stock.get(key);
    }
    
    public static void addCode(String key, String code) {
        if(null == stock) {
            stock = new HashMap<String, String>();
        }
        stock.put(key, code);
    }
}
