fun main() {
    val sequenceLength = readln().toInt()

    val numbers = mutableListOf<Int>()
    repeat(sequenceLength) {
        numbers.add(readln().toInt())
    }

    val rotation = 1

    val result = numbers.toMutableList()

    for (i in 0..numbers.lastIndex) {
        if (i + rotation <= numbers.lastIndex) {
            result[i + rotation] = numbers[i]
        } else {
            result[(i + rotation) % numbers.size] = numbers[i]
        }
    }

    for (n in result) {
        print("$n ")
    }
}