const val TEN = 10
fun main() {
    var num = readln().toInt()
    while (num != 0) {
        print(num % TEN)
        num /= TEN
    }
}