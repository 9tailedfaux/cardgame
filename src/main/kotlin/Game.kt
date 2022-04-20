import com.sun.org.apache.xpath.internal.operations.Bool

class Game(val cards: Cards, val playerFirst: Boolean) {
    var myScore = 0
    var playerScore = 0
    val algorithm = Algorithm(cards)
    var ended = false

    fun choose(top: Boolean, player: Boolean) {
        val card: Card = if (top) cards.takeLast()!! else cards.takeFirst()!!
        if (player) playerScore += card.value else myScore += card.value
        if (cards.first > cards.last) ended = true
    }
}