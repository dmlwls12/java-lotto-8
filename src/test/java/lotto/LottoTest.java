package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test void lotto_must_have_6_unique_numbers_in_1_to_45() {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(Arrays.asList(1,2,3,4,5)));
        assertThrows(IllegalArgumentException.class, () -> new Lotto(Arrays.asList(1,2,3,4,5,46)));
        assertThrows(IllegalArgumentException.class, () -> new Lotto(Arrays.asList(1,1,3,4,5,6)));
        assertDoesNotThrow(() -> new Lotto(Arrays.asList(1,2,3,4,5,6)));
    }

    @Test void lotto_is_sorted_ascending() {
        Lotto l = new Lotto(Arrays.asList(6,5,4,3,2,1));
        assertEquals(Arrays.asList(1,2,3,4,5,6), l.getNumbers());
    }
}
