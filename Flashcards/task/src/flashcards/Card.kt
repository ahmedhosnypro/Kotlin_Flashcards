package flashcards

class Card {
    val term: String
    var definition: String
    var wrongAnswersCount = 0
        private set

    constructor(term: String, definition: String) {
        this.term = term
        this.definition = definition
    }

    constructor(term: String, definition: String, wrongAnswers: Int) {
        this.term = term
        this.definition = definition
        wrongAnswersCount = wrongAnswers
    }

    fun incrementWrongAnswers() {
        wrongAnswersCount++
    }

    fun resetWrongAnswers() {
        wrongAnswersCount = 0
    }

    fun setWrongAnswers(wrongAnswers: Int) {
        wrongAnswersCount = wrongAnswers
    }
}