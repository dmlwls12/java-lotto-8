package lotto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    @Test
    void amount_must_be_positive_and_divisible_by_1000() {
        assertThrows(IllegalArgumentException.class, () -> Money.of(0));
        assertThrows(IllegalArgumentException.class, () -> Money.of(1500));
        assertDoesNotThrow(() -> Money.of(3000));
    }

    @Test void toTicketCount_returns_amount_divided_by_1000() {
        assertEquals(3, Money.of(3000).toTicketCount());
    }

}
