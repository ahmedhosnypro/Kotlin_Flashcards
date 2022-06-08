package flashcards

fun main() {
    println("Input the number of cards:")
    val cards = readln().toInt()

    val terms = mutableListOf<String>()
    val definitions = mutableListOf<String>()

    for (i in 1..cards) {
        println("Card #$i:")
        terms.add(readln())
        println("The definition for card #$i:")
        definitions.add(readln())
    }

    for (i in 0 until cards) {
        println("Print the definition of \"${terms[i]}\"")
        val userInput = readln()
        if (userInput == definitions[i]) {
            println("Correct!")
        } else {
            println("Wrong. The right answer is \"${definitions[i]}\".")
        }
    }
}