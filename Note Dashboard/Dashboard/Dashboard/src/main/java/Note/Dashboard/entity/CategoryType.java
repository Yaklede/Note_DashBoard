package Note.Dashboard.entity;

public enum  CategoryType {
    BUSINESS("Business"),
    SOCIAL("Social"),
    IMPORTANT("Important");

    private final String displayValue;

    private CategoryType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
