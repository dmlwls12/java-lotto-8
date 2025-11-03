package lotto;

public final class Money {
    public static final int LOTTO_PRICE = 1_000;
    private final long amount;

    private Money(long amount) {
        if (amount <= 0 || amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위의 양의 정수여야 합니다.");
        }
        this.amount = amount;
    }

    public static Money of(long amount) {
        return new Money(amount);
    }

    public long amount() {
        return amount;
    }

    public int toTicketCount() {
        return (int) (amount / LOTTO_PRICE);
    }
}
