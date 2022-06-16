const val SPD = 2 // students per desk
fun main() {
    val class1 = readln().toInt()
    val class2 = readln().toInt()
    val class3 = readln().toInt()

    print(class1 / SPD + class1 % SPD + class2 / SPD + class2 % SPD + class3 / SPD + class3 % SPD)
}