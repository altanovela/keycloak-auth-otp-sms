package org.metranet.keycloak.otp.provider;

import org.keycloak.services.resource.RealmResourceProvider;
import org.metranet.keycloak.otp.api.RequestOtpController;

public class RequestOtpProvider implements RealmResourceProvider {
    
    @Override
    public void close() {
        // do nothing
    }

    @Override
    public Object getResource() {
        return new RequestOtpController();
    }
}
