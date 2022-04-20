import java.util.StringJoiner

class Cards() : ArrayList<Card>() {
    var last = 0
    var first = 0

    fun peekLast(): Card? {
        return if (first <= last) get(last) else null
    }

    fun peekFirst(): Card? {
        return if (first <= last) get(first) else null
    }

    fun takeLast(): Card? {
        return if (peekLast() == null) {
            null
        } else {
            get(last--)
        }
    }

    fun takeFirst(): Card? {
        return if (peekFirst() == null) {
            null
        } else {
            get(first++)
        }
    }

    override fun add(element: Card): Boolean {
        last = size
        return super.add(element)
    }

    override fun add(index: Int, element: Card) {
        last = size
        super.add(index, element)
    }

    override fun addAll(elements: Collection<Card>): Boolean {
        last += elements.size
        return super.addAll(elements)
    }

    override fun addAll(index: Int, elements: Collection<Card>): Boolean {
        last += elements.size
        return super.addAll(index, elements)
    }

    private fun printRow(string: String) {
        for (i in first..last) {
            print(string)
        }
        println()
    }

    private fun printRow(f: (i: Int) -> String) {
        for (i in first..last) {
            print(f(i))
        }
        println()
    }

    fun print() {
        printRow(" ______  ")
        printRow("|      | ")
        printRow {
            "| ${get(it).value.toString().padEnd(4, ' ')} | "
        }
        printRow("|      | ")
        printRow("|______| ")
    }
}