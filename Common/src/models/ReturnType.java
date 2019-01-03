package models;

public enum ReturnType {
	ok(1),
	doNotHavePermission(2),
	alreadyExists(3),
	alreadyRemoved(4);
	private final int value;
	private ReturnType(int value) {
        this.value = value;
    }
}
