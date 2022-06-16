fun main() {
    val product = readln().toInt() * readln().toInt()
    if (product == 0) {
        print("It can't be zero!")
    } else {
        println(product)
    }
}