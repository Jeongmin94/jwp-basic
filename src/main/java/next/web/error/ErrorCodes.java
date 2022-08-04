package next.web.error;

public enum ErrorCodes {
    USER_LOGIN_FAILED(404, "LOGIN_FAILED");
    private final int errorCode;
    private final String errorMessage;

    ErrorCodes(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
