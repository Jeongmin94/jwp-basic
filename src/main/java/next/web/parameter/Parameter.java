package next.web.parameter;

public enum Parameter {
    ID("userId"),
    PASSWORD("password");

    private final String value;

    Parameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
