package api;

public enum StatusEnum {

    MISSING_NAME("Missing required field: name"),
    INVALID_TOKEN("Invalid access token");

    private final String errorDescription;

    public String getErrorDescription() { return errorDescription;}

    StatusEnum(String errorDescription) { this.errorDescription = errorDescription;}
}
