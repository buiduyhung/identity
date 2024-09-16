package com.devtia.identity.exception;

public enum ErrorCode {
    INVALID_KEY(1001, "Invalid message key"),
    USER_EXITED(1002, "User exited"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters"),
    USER_NOT_EXITED(1005, "User not exited"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
