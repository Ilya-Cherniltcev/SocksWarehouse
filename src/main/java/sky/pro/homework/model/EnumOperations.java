package sky.pro.homework.model;

public enum EnumOperations {
    MORE_THAN ("moreThan"),
    LESS_THAN ("lessThan"),
    EQUAL ("equal");

    private final String operation;

    EnumOperations(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
