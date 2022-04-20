fun main() {
    val cards = getCards()
    println("Here are the cards")
    cards.print()
    val goingFirst = getBoolean("Are you going first?")
    val game = Game(cards, goingFirst)
    println("Here is my strategy")
    game.algorithm.printStrategy()
    if (goingFirst) println("I can't guarantee I'll win unless I go first, but I'll still try my best!")
    else println("You're going down!")
    println("Let's play!")
    gameLoop(game)
}

fun gameLoop(game: Game) {
    while (!game.ended){
        if (game.playerFirst) {
            yourTurn(game)
            myTurn(game)
        } else {
            myTurn(game)
            yourTurn(game)
        }
    }
    println("Game over!\nMy score: ${game.myScore}\nYour score: ${game.playerScore}\nPlay me again some time!")
}

fun myTurn(game: Game) {
    game.cards.print()
    val choice = game.algorithm.getMove(game.cards.first, game.cards.last)
    if (choice) println("I chose the last card") else println("I chose the first card")
    game.choose(choice, false)
    println("I now have ${game.myScore} points")
}

fun yourTurn(game: Game) {
    game.cards.print()
    game.choose(getBoolean("Will you take the last card? You get the first one otherwise"), true)
    println("You now have ${game.playerScore} points")
}

fun getCards(): Cards {
    var userInput = ""
    val cards = Cards()
    while (!userInput.contentEquals("done", true)) {
        print("Enter an integer value for the next card or enter \"done\" to finish: ")
        userInput = readln()
        val value = userInput.toIntOrNull()
        value?.let { cards.add(Card(it)) }
        if (cards.size % 2 != 0 && userInput.contentEquals("done", true)) {
            println("Must have an even number of cards. Add one more please!")
            userInput = ""
        }
    }
    return cards
}

fun getBoolean(question: String?): Boolean {
    var boolean: Boolean? = null
    while (boolean == null) {
        print("$question (y/n/yes/no/true/false): ")
        val input = readln()
        boolean = if (input.contentEquals("yes", true) ||
                input.contentEquals("y", true) ||
                input.contentEquals("true", true)) {
            true
        } else if (input.contentEquals("no", true) ||
                input.contentEquals("n", true) ||
                input.contentEquals("false", true)) {
            false
        } else null
    }
    return boolean
}