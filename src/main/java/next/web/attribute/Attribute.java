package next.web.attribute;

public enum Attribute {
    MESSAGE("message");

    private final String value;

    Attribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
