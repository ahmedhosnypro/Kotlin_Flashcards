fun main() {
    val studentsMarks = mutableMapOf<String, Int>()

    var word = readln()
    while (word != "stop") {
        studentsMarks.putIfAbsent(word, readln().toInt())
        word = readln()
    }

    println(studentsMarks)
}