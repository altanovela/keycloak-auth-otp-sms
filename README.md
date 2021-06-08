# Keycloak OTP SMS Authentication

[![Java](https://img.shields.io/badge/Java-1.8.0-red.svg?style=plastic)](https://www.oracle.com/java/technologies/)
[![Keycloak](https://img.shields.io/badge/Keycloak-11.0.2-important.svg?style=plastic)](https://www.keycloak.org)
[![Maven](https://img.shields.io/badge/Maven-3.5.3-purple.svg?style=plastic)](https://maven.apache.org)

Plugin ini di buat sebagai Flow Authentikasi alternatif untuk Keycloak Server. keynote :
- Login dengan OTP Code yang dikirim ke Phone Number.
- Registrasi dengan OTP Code yang dikirim ke Phone Number.
- Implementasi SMS Gateway/Media lain, silahkan sesuaikan pada fungsi [OtpSmsSender.sendSMS(...)](https://github.com/altanovela/keycloak-auth-otp-sms/blob/62919c6b5f884111dcbf42b8e71c762a111245c8/src/main/java/org/metranet/keycloak/otp/util/OtpSmsSender.java#L9)

#### 1. Compile dan Build
```
$ mvn -e clean install
```

#### 2. Integrasi dengan Keycloak
```
## Buat Tema baru
mkdir ${KEYCLOAK_HOME}/themes/otpsms
cp ${PROJECT_HOME}/resources/themes/otpsms/* ${KEYCLOAK_HOME}/themes/otpsms/

## Deploy Jar ke Keycloak
cp ${PROJECT_HOME}/target/otp-sms-1.0.0.jar ${KEYCLOAK_HOME}/standalone/deployments/otp-sms-1.0.0.jar
```

#### 3. Restart Keycloak Server

#### 4. Setup Tema
- Akses halaman Admin Keycloak.
- Pilih Realm, kemudian buka Menu `Realm Settings` tab `Themes`.
- Pilih `Login Theme` dengan `otpsms` (sesuai dengan folder Tema yang di buat sebelumnya) 

#### 5. Setup Authentication Flow di Keycloak Admin untuk Sign In
- Akses halaman Admin Keycloak.
- Pilih Realm, kemudian buka Menu `Authentication` tab `Flow`.
- Copy `Browser` flow dan beri nama `Browser OTP SMS`.
- Di bagian `Auth Type`, dalam `Browser OTP SMS Forms` hapus `Username Password Form` dan `Browser OTP SMS Browser - Conditional OTP`, melalui link `Action` di pojok kanan baris.
- Kemudian tambahkan executor baru di dalam `Browser OTP SMS Forms` dengan memilih Action > Add execution, pilih Provider `OTP Using SMS`, save.
- Ubah Requirement dari OTP Using SMS menjadi `REQUIRED`, sehingga tampilan akhir Authentication nya menjadi seperti di bawah ini.

![](https://github.com/altanovela/keycloak-auth-otp-sms/raw/master/resources/images/otp-sms-authentication-flow.jpg)

- Selanjutnya buka Menu `Authentication` tab `Bindings`.
- Ubah `Borwser Flow` menjadi `Browser OTP SMS`
- Done.

#### 6. Setup Authentication Flow di Keycloak Admin untuk Sign Up
- Akses halaman Admin Keycloak.
- Pilih Realm, kemudian buka Menu `Authentication` tab `Flow`.
- Copy `Registration` flow dan beri nama `Registration OTP SMS`.
- Di bagian `Auth Type`, dalam `Registration OTP SMS Registration Form` melalui link `Action` di pojok kanan baris, tambahkan executor baru dengan memilih Action > Add execution, pilih Provider `OTP Using SMS Registration`, save.
- Kemudian ubah posisi di `OTP Using SMS Registration` menjadi paling atas dalam flow registrasi, sehingga tampilan akhirnya menjadi seperti ini.

![](https://github.com/altanovela/keycloak-auth-otp-sms/raw/master/resources/images/otp-sms-authentication-flow-signup.jpg)

#### 7. Preview Sign In dan Sign Up
![](https://github.com/altanovela/keycloak-auth-otp-sms/raw/master/resources/images/sms-login-page.jpg)
![](https://github.com/altanovela/keycloak-auth-otp-sms/raw/master/resources/images/sms-otp-page.jpg)
![](https://github.com/altanovela/keycloak-auth-otp-sms/raw/master/resources/images/sms-registration-page.jpg)

Notes : Referensi Tema yang di gunakan pada Preview Page di atas, bisa mengacu ke [project ini](https://github.com/altanovela/keycloak-auth-otp-sms.git)

## Contributors
| Name | Email |
| ------------ | ------------ |
| Rio Bastian | rio.bastian@metranet.co.id |
|  | altanovela@gmail.com |
