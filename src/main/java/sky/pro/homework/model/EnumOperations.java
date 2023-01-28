package sky.pro.homework.model;

public enum EnumOperations {
    moreThan ("moreThan"),
    lessThan ("lessThan"),
    equal ("equal");

    private final String operation;

    EnumOperations(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
