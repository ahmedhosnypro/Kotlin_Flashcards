fun solution(strings: MutableList<String>, str: String) = strings
    .map { if (it == str) "Banana" else it }
    .toMutableList()