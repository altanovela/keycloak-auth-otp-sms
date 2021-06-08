package org.metranet.keycloak.otp.factory;

import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;
import org.metranet.keycloak.otp.provider.RequestOtpProvider;

public class RequestOtpProviderFactory implements RealmResourceProviderFactory {

    public static final String ID = "otpsms";
    
    @Override
    public RealmResourceProvider create(KeycloakSession session) {
        return new RequestOtpProvider();
    }

    @Override
    public void init(Scope config) {
        // do nothing
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // do nothing
    }

    @Override
    public void close() {
        // do nothing
    }

    @Override
    public String getId() {
        return ID;
    }

}
