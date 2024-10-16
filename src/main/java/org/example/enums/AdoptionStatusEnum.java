package org.example.enums;

public enum AdoptionStatusEnum {
    PENDING(1),
    UNDER_REVIEW(2),
    APPROVED(3),
    REJECTED(4),
    WAITING_FOR_PAYMENT(5),
    PAYMENT_COMPLETED(6),
    FINALIZING_ADOPTION(7),
    COMPLETED(8),
    CANCELLED(9);

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

