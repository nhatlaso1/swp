package org.example.enums;

public enum AdoptionStatusEnum {
    PENDING(1),
    AVAILABLE(2),
    REVIEW(3),
    APPROVED(4),
    REJECTED(5);

    private final int value;

    private AdoptionStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AdoptionStatusEnum fromValue(int value) {
        for (AdoptionStatusEnum status : AdoptionStatusEnum.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status value: " + value);
    }
}

