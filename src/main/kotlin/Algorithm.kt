import org.omg.PortableServer.POA
import kotlin.math.min

class Algorithm(private val cards: Cards) {
    private val strategyMatrix = getStrategy()

    fun getMove(i: Int, j: Int): Boolean {
        return strategyMatrix[i][j]!!.top
    }

    fun printStrategy() {
        print("          ")
        for (i in 0 until cards.size) {
            print(i.toString().padEnd(13, ' '))
        }
        println()
        for (i in 0 until cards.size) {
            print("${i.toString().padStart(5, ' ')} |")
            for (j in 0 until cards.size) {
                val possibility = strategyMatrix[j][i]
                possibility?.let { print(" ${it.score.toString().padStart(3, ' ')}(${it.top.toString().padStart(5, ' ')}) |" ) }
            }
            println()
        }
    }

    private fun getStrategy(): Array<Array<Possibility?>> {
        val strategy = Array(cards.size) {Array<Possibility?>(cards.size) {null} }

        for (x in 0 until cards.size) {
            for ((i, j) in (x until cards.size).withIndex()) {
                val left: Int?
                val middle: Int?
                val right: Int?
                if (i > j - 2) {
                    left = 0
                    middle = 0
                    right = 0
                }
                else {
                    left = strategy[i + 2][j]!!.score
                    middle = strategy[i + 1][j - 1]!!.score
                    right = strategy[i][j - 2]!!.score
                }
                val bottom = cards[i].value + min(left, middle)
                val top = cards[j].value + min(middle, right)
                if (top > bottom) {
                    strategy[i][j] = Possibility(top, true)
                } else {
                    strategy[i][j] = Possibility(bottom, false)
                }
            }
        }

        return strategy
    }
}