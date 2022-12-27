package minesweeper.domain

class Position(private val rows: Int, private val columns: Int, private val position: Int) {

    val top = makeTop()
    val topLeft = makeTopLeft()
    val topRight = makeTopRight()

    val left = makeLeft()
    val right = makeRight()

    val bottom = makeBottom()
    val bottomLeft = makeBottomLeft()
    val bottomRight = makeBottomRight()

    private fun makeBottomRight(): Int {
        if (bottom < 0) return -1
        if ((bottom + 1) % columns <= 0) return -1
        return bottom + 1
    }

    private fun makeBottomLeft(): Int {
        if (bottom % columns <= 0) return -1
        return bottom - 1
    }

    private fun makeBottom(): Int {
        val diff = position + columns
        if (diff > (rows * columns - 1)) return -1
        return diff
    }

    private fun makeRight(): Int {
        if ((position + 1) % columns <= 0) return -1
        return position + 1
    }

    private fun makeLeft(): Int {
        if (position == 0) return -1
        if (position % columns <= 0) return -1
        return position - 1
    }

    private fun makeTopRight(): Int {
        if (top < 0) return -1
        if ((top + 1) % columns <= 0) return -1
        return top + 1
    }

    private fun makeTopLeft(): Int {
        if (top % columns <= 0) return -1
        return top - 1
    }

    private fun makeTop(): Int {
        val diff = position - columns
        if (diff < 0) return -1
        return diff
    }

    override fun toString(): String {
        return """
            Position $position
            |$topLeft|$top|$topRight|
            |$left|$position|$right|
            |$bottomLeft|$bottom|$bottomRight|
        """.trimIndent()
    }
}
