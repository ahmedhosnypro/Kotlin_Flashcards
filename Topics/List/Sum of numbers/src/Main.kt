fun solution(numbers: List<Int>): Int {
    return numbers.stream().mapToInt { it }.sum()
}