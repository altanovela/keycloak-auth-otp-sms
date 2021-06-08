<#import "template.ftl" as layout>
<@layout.registrationLayout displayAuthUserInfo=false displayInfo=social.displayInfo; section>
    <#if section = "header">
        <span>Sign In </span><span style="color:red">Aku.Id</span>
    <#elseif section = "form">
        <#if realm.password>
            <form id="kc-form-login" class="${properties.kcFormClass!}" onsubmit="login.disabled = true; return true;" action="${url.loginAction}" method="post">
                <input type="hidden" name="page_type" value="sms_otp_page" />
                <div class="${properties.kcFormGroupClass!}">
                    <div class="mdc-text-field mdc-text-field--with-leading-icon ${properties.kcLabelClass!} <#if usernameEditDisabled??>mdc-text-field--disabled</#if>">
                        <i class="material-icons mdc-text-field__icon" role="button">code</i>
                        <input tabindex="0" required id="sms_otp_user" class="mdc-text-field__input ${properties.kcInputClass!}" name="sms_otp_user" type="text" autofocus autocomplete="off">
                        <div class="mdc-line-ripple"></div>
                        <label for="sms_otp_user" class="mdc-floating-label ${properties.kcLabelClass!}">
                            OTP
                        </label>
                    </div>
                </div>
                
                <div class="mdc-card__actions">
                    <a href="${client.baseUrl}" class="mdc-button mdc-card__action mdc-card__action--button">
                        <i class="material-icons mdc-button__icon">arrow_back</i>Login Page
                    </a>
                    
                    <div class="mdc-card__action-icons">
                        <div class="mdc-card__action-buttons">
                            <button tabindex="0" name="login" id="kc-login" type="submit" class="mdc-button mdc-button--raised mdc-card__action">
                                Login
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </#if>
    </#if>
</@layout.registrationLayout>
