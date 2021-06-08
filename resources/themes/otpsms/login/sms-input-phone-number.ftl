<#import "template.ftl" as layout>
<@layout.registrationLayout displayInfo=social.displayInfo; section>
    <#if section = "header">
        <span>Sign In </span><span style="color:red">Aku.Id</span>
    <#elseif section = "form">
        <#if realm.password>
            <form id="kc-form-login" class="${properties.kcFormClass!}" onsubmit="login.disabled = true; return true;" action="${url.loginAction}" method="post">
                <input type="hidden" name="page_type" value="login_page" />
                <div class="${properties.kcFormGroupClass!}">
                    <div class="mdc-text-field mdc-text-field--with-leading-icon ${properties.kcLabelClass!} <#if usernameEditDisabled??>mdc-text-field--disabled</#if>">
                        <i class="material-icons mdc-text-field__icon" role="button">phone</i>
                        <input tabindex="0" required id="user.attributes.mobile_number" class="mdc-text-field__input ${properties.kcInputClass!}" name="user.attributes.mobile_number" type="text" autofocus autocomplete="off">
                        <div class="mdc-line-ripple"></div>
                        <label for="user.attributes.mobile_number" class="mdc-floating-label ${properties.kcLabelClass!}">
                            Phone Number
                        </label>
                    </div>
                </div>
                
                <div class="mdc-card__actions">
                    <a href="${url.registrationUrl}" class="mdc-button mdc-card__action mdc-card__action--button">
                        <i class="material-icons mdc-button__icon">arrow_back</i>Sign Up
                    </a>
                    
                    <div class="mdc-card__action-icons">
                        <div class="mdc-card__action-buttons">
                            <button tabindex="0" name="login" id="kc-login" type="submit" class="mdc-button mdc-button--raised mdc-card__action">
                                Request OTP
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </#if>
    </#if>
</@layout.registrationLayout>
