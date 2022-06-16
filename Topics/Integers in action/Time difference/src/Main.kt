fun main() {
    val secInHour = 60 * 60
    val secInMin = 60
    val hour1 = readln().toInt() * secInHour
    val minute1 = readln().toInt() * secInMin
    val second1 = readln().toInt()

    val hour2 = readln().toInt() * secInHour
    val minute2 = readln().toInt() * secInMin
    val second2 = readln().toInt()

    println(hour2 - hour1 + minute2 - minute1 + second2 - second1)
}