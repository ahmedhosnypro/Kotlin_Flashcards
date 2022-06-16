package flashcards

import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.io.IOException
import java.util.*
import java.util.function.Consumer

private const val STRING_FILE_NAME = "File name:"

object Main {
    private var logs = StringBuilder()
    private var cards = mutableListOf<Card>()

    @JvmStatic
    fun main(args: Array<String>) {
        startAction()
    }

    private fun startAction() {
        print("\nInput the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):")
        when (read()) {
            "add" -> {
                addCard()
                startAction()
            }
            "remove" -> {
                removeCard()
                startAction()
            }
            "import" -> {
                importCards()
                startAction()
            }
            "export" -> {
                exportCards()
                startAction()
            }
            "ask" -> {
                ask()
                startAction()
            }
            "log" -> {
                log()
                startAction()
            }
            "hardest card" -> {
                hardestCard()
                startAction()
            }
            "reset stats" -> {
                resetStats()
                startAction()
            }
            "exit" -> println("Bye bye!")
            else -> startAction()
        }
    }

    private fun addCard() {
        print("The card:")
        val term = read()
        for (card in cards) {
            if (card.term == term) {
                print("The card \"$term\" already exists.")
                return
            }
        }
        print("The definition of the card:")
        val definition = read()
        for (card in cards) {
            if (card.definition == definition) {
                print("The definition \"$definition\" already exists.")
                return
            }
        }
        cards.add(Card(term, definition))
        print("The pair (\"$term\":\"$definition\") has been added.")
    }

    private fun removeCard() {
        print("Which card?")
        val term = read()
        var isRemoved = false
        val it = cards.iterator()
        while (it.hasNext()) {
            val card = it.next()
            if (card.term == term) {
                it.remove()
                print("The card has been removed.")
                isRemoved = true
                break
            }
        }
        if (!isRemoved) {
            print("Can't remove \"$term\": there is no such card.")
        }
    }

    private fun importCards() {
        var importedCards = 0
        print(STRING_FILE_NAME)
        val fileName = read()
        val file = File(fileName)
        if (file.exists()) {
            try {
                Scanner(file).use { fileScanner ->
                    while (fileScanner.hasNext()) {
                        val term = fileScanner.nextLine()
                        val definition = fileScanner.nextLine()
                        val wrongAnswers = fileScanner.nextLine().toInt()
                        importedCards += importedCard(term, definition, wrongAnswers)
                    }
                    print("$importedCards cards have been loaded.")
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        } else {
            print("File not found.")
        }
    }

    private fun importedCard(term: String, definition: String, wrongAnswers: Int): Int {
        for (card in cards) {
            if (card.term == term) {
                card.definition = definition
                card.setWrongAnswers(wrongAnswers)
                return 1
            }
        }
        for (card in cards) {
            if (card.definition == definition) {
                return 0
            }
        }
        cards.add(Card(term, definition, wrongAnswers))
        return 1
    }

    private fun exportCards() {
        var exportCards = 0
        print(STRING_FILE_NAME)
        val fileName = read()
        val file = File(fileName)
        try {
            FileWriter(file).use { writer ->
                for ((index, card) in cards.withIndex()) {
                    if (index == cards.size - 1) {
                        writer.write((card.term + "\n" + card.definition) + "\n" + card.wrongAnswersCount)
                    } else {
                        writer.write(((card.term + "\n" + card.definition) + "\n" + card.wrongAnswersCount) + "\n")
                    }
                    exportCards++
                }
                print("$exportCards cards have been saved.")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun ask() {
        print("How many times to ask?")
        val times = read().toInt()
        for (i in 0 until times) {
            print("Print the definition of \"" + cards[i].term + "\":")
            val definition = read()
            if (definition == cards[i].definition) {
                print("Correct!")
            } else if (findDefinition(definition)) {
                print(
                    "Wrong. The right answer is \"" + cards[i].definition + "\", but your definition is correct for \"" + rightCard(
                        definition
                    ) + "\""
                )
                cards[i].incrementWrongAnswers()
            } else {
                print("Wrong. The right answer is \"" + cards[i].definition + "\".")
                cards[i].incrementWrongAnswers()
            }
        }
    }

    private fun log() {
        print(STRING_FILE_NAME)
        val fileName = read()
        try {
            FileWriter(fileName).use { writer ->
                writer.write(logs.toString())
                print("The log has been saved.")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun hardestCard() {
        if (cards.stream().anyMatch { it.wrongAnswersCount > 0 }) {
            val maxWrongAnswersLevel = wrongAnswersLevel()
            val hardestCard = getHardCards(maxWrongAnswersLevel)
            val stats = StringBuilder()
            if (hardestCard.size > 1) {
                stats.append("The hardest cards are ")
                hardestCard.forEach(Consumer { term: String? ->
                    stats.append("\"").append(term).append("\", ")
                })
                stats.replace(stats.length - 2, stats.length - 1, "")
                stats.append(". You have ").append(maxWrongAnswersLevel).append(" errors answering them.")
            } else if (hardestCard.size == 1) {
                stats.append("The hardest card is ")
                hardestCard.forEach(Consumer { term: String? ->
                    stats.append("\"").append(term).append("\"")
                })
                stats.append(". You have ").append(maxWrongAnswersLevel).append(" errors answering it.")
            }
            print(stats.toString())
        } else {
            print("There are no cards with errors.")
        }
    }

    private fun resetStats() {
        cards.forEach(Consumer { obj: Card -> obj.resetWrongAnswers() })
        print("Card statistics have been reset.")
    }

    private fun print(output: String) {
        println(output)
        logs.append(output).append("\n")
    }

    private fun read(): String {
        val input = readln()
        logs.append(input).append("\n")
        return input
    }

    private fun findDefinition(definition: String): Boolean {
        return cards.stream().anyMatch { card: Card ->
            card.definition == definition
        }
    }

    private fun rightCard(definition: String): String {
        for (card in cards) {
            if (card.definition == definition) {
                return card.term
            }
        }
        return ""
    }

    private fun wrongAnswersLevel(): Int = cards.stream()
        .mapToInt { it.wrongAnswersCount }
        .reduce(0) { a: Int, b: Int ->
            a.coerceAtLeast(b)
        }


    private fun getHardCards(maxWrongAnswersLevel: Int): MutableList<String> = cards.stream()
        .filter { card: Card -> card.wrongAnswersCount == maxWrongAnswersLevel }
        .map { it.term }
        .toList()
}