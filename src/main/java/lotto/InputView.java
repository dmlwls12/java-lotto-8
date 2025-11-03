package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class InputView {

    private static final int MIN = 1;
    private static final int MAX = 45;

    public static long readPurchaseAmount() {
        System.out.println("구입금액을 입력해주세요.");
        String line = Console.readLine();
        try {
            return Long.parseLong(line.trim());
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자만 입력 가능합니다.");
        }
    }

    public static List<Integer> readWinningNumbers() {
        System.out.println("\n당첨 번호를 입력해주세요.");
        String line = Console.readLine();
        String[] parts = line.split(",");

        if (parts.length != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 쉼표로 구분된 6개여야 합니다.");
        }

        List<Integer> numbers = new ArrayList<>();
        Set<Integer> uniqueNumbers = new HashSet<>();

        for (int i = 0; i < parts.length; i++) {
            String trimmed = parts[i].trim();
            if (trimmed.isEmpty()) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 쉼표로 구분된 6개여야 합니다.");
            }

            int value;

            try {
                value = Integer.parseInt(trimmed);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
            }

            if (value < MIN || value > MAX) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
            if (!uniqueNumbers.add(value)) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
            }

            numbers.add(value);
        }

        return numbers;
    }

    public static int readBonusNumber(Set<Integer> winningNumbers) {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        String line = Console.readLine();
        int bonus;
        try {
            bonus = Integer.parseInt(line.trim());
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
        if (bonus < MIN || bonus > MAX) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
        }
        if (winningNumbers.contains(bonus)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
        return bonus;
    }
}
