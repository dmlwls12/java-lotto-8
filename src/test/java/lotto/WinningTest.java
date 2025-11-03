package lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningTest {
    @Test
    void bonus_cannot_be_duplicate_or_out_of_range() {
        assertThrows(IllegalArgumentException.class, () -> new Winning(Arrays.asList(1,2,3,4,5,6), 6));
        assertThrows(IllegalArgumentException.class, () -> new Winning(Arrays.asList(1,2,3,4,5,6), 0));
        assertThrows(IllegalArgumentException.class, () -> new Winning(Arrays.asList(1,2,3,4,5,6), 46));
    }

    @Test void judge_2nd_when_5_matches_and_bonus_matches() {
        Winning w = new Winning(Arrays.asList(1,2,3,4,5,6), 7);
        Lotto t = new Lotto(Arrays.asList(1,2,3,4,5,7));
        assertEquals(Rank.SECOND, w.judge(t));
    }

}
