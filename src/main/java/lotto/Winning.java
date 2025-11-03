package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Winning {
    private final Set<Integer> winningNumbers;
    private final int bonus;

    public Winning(List<Integer> numbers, int bonus) {
        Lotto temp = new Lotto(numbers);
        this.winningNumbers = new HashSet<>(temp.getNumbers());

        if (bonus < 1 || bonus > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
        }
        if (winningNumbers.contains(bonus)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
        this.bonus = bonus;
    }

    public Rank judge(Lotto ticket) {
        int matchCount = ticket.countMatchWith(winningNumbers);
        boolean bounusMatch = (matchCount == 5) && ticket.contains(bonus);
        return Rank.from(matchCount, bounusMatch);
    }
}
