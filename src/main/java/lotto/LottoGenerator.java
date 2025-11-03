package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public final class LottoGenerator {
    public Lotto createRandomTicket() {
        List<Integer> nums = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(nums);
    }
}
