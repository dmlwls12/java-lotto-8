package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public final class InputView {

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
        List<Integer> numbers = new ArrayList<>();

        for (String part : parts) {
            String trimmed = part.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            try {
                numbers.add(Integer.parseInt(trimmed));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
            }
        }

        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 쉼표로 구분된 6개여야 합니다.");
        }

        return numbers;
    }

    public static int readBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        String line = Console.readLine();
        try {
            return Integer.parseInt(line.trim());
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
    }
}
