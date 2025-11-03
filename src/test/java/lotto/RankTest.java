package lotto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RankTest {
    @Test void rank_from_rules() {
        assertEquals(Rank.FIRST,  Rank.from(6, false));
        assertEquals(Rank.SECOND, Rank.from(5, true));
        assertEquals(Rank.THIRD,  Rank.from(5, false));
        assertEquals(Rank.FOURTH, Rank.from(4, false));
        assertEquals(Rank.FIFTH,  Rank.from(3, false));
        assertEquals(Rank.NONE,   Rank.from(2, false));
    }

}
