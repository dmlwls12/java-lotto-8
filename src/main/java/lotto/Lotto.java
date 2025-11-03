package lotto;

import java.util.*;

public class Lotto {
    private static final int SIZE = 6;
    private static final int MIN = 1;
    private static final int MAX = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        List<Integer> copy = new ArrayList<>(numbers);
        Collections.sort(copy);
        this.numbers = Collections.unmodifiableList(copy);
    }

    private void validate(List<Integer> numbers) {
        if (numbers == null || numbers.size() != SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (Integer number : numbers) {
            if (number == null || number < MIN || number > MAX) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
            if (!uniqueNumbers.add(number)) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
            }
        }
    }

    public List<Integer> getNumbers() { return numbers; }

    public int countMatchWith(Set<Integer> winningNumbers) {
        int count = 0;
        for (int number : numbers) {
            if (winningNumbers.contains(number)) count++;
        }
        return count;
    }

    public boolean contains(int number) { return numbers.contains(number); }

    @Override
    public String toString() { return numbers.toString(); }
}
