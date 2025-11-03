package lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultTest {
    @Test
    void result_aggregates_counts_and_total_prize() {
        Winning w = new Winning(Arrays.asList(1,2,3,4,5,6), 7);
        List<Lotto> tickets = Arrays.asList(
                new Lotto(Arrays.asList(1,2,3,4,5,6)), // 1등
                new Lotto(Arrays.asList(1,2,3,4,5,7)), // 2등
                new Lotto(Arrays.asList(1,2,3,4,5,10)) // 3등
        );
        Result r = Result.of(tickets, w);
        assertEquals(1, r.counts().get(Rank.FIRST));
        assertEquals(1, r.counts().get(Rank.SECOND));
        assertEquals(1, r.counts().get(Rank.THIRD));
        assertEquals(2_000_000_000L + 30_000_000L + 1_500_000L, r.totalPrize());
    }

}
