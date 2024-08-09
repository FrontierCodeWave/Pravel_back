package com.tour.prevel.auth.utils;

public class Validator {

    private static final String EMAIL_REGEX = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
    private static final String PASSWORD_REGEX = "^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$";
    private static final String NICKNAME_REGEX = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$";

    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public static boolean isValidNickname(String nickname) {
        return nickname.matches(NICKNAME_REGEX);
    }
}
