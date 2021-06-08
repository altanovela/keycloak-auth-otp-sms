<#import "template.ftl" as layout>
<@layout.registrationLayout displayAuthUserInfo=false displayInfo=social.displayInfo; section>
    <#if section = "header">
        <span>Sign In </span><span style="color:red">Aku.Id</span>
    <#elseif section = "form">
        <#if realm.password>
            <form id="kc-totp-login-form" class="${properties.kcFormClass!}" action="${url.loginAction}" method="post">
                <div class="${properties.kcFormGroupClass!}">
                    <div id="kc-form-options" class="${properties.kcFormOptionsClass!}">
                        <div class="${properties.kcFormOptionsWrapperClass!}">
                        </div>
                    </div>
                </div>
                
                <div class="mdc-card__actions">
                    <a href="${client.baseUrl}" class="mdc-button mdc-card__action mdc-card__action--button">
                        <i class="material-icons mdc-button__icon">arrow_back</i>Login Page
                    </a>
                </div>
            </form>
        </#if>
    </#if>
</@layout.registrationLayout>
