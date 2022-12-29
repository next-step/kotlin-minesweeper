package minesweeper.domain

class Position(rows: Int, columns: Int, private val position: Int) {

    val top = makeTop(position, columns)
    val topLeft = makeTopLeft(columns)
    val topRight = makeTopRight(columns)

    val left = makeLeft(position, columns)
    val right = makeRight(position, columns)

    val bottom = makeBottom(position, rows, columns)
    val bottomLeft = makeBottomLeft(columns)
    val bottomRight = makeBottomRight(columns)

    private fun makeBottomRight(columns: Int): Int {
        if (bottom < 0) return -1
        val bottomRight = bottom + 1
        if (bottomRight % columns <= 0) return -1
        return bottomRight
    }

    private fun makeBottomLeft(columns: Int): Int {
        if (bottom % columns <= 0) return -1
        return bottom - 1
    }

    private fun makeBottom(position: Int, rows: Int, columns: Int): Int {
        val diff = position + columns
        if (diff > (rows * columns - 1)) return -1
        return diff
    }

    private fun makeRight(position: Int, columns: Int): Int {
        val right = position + 1
        if (right % columns <= 0) return -1
        return right
    }

    private fun makeLeft(position: Int, columns: Int): Int {
        if (position == 0) return -1
        if (position % columns <= 0) return -1
        return position - 1
    }

    private fun makeTopRight(columns: Int): Int {
        if (top < 0) return -1
        val topRight = top + 1
        if (topRight % columns <= 0) return -1
        return topRight
    }

    private fun makeTopLeft(columns: Int): Int {
        if (top % columns <= 0) return -1
        return top - 1
    }

    private fun makeTop(position: Int, columns: Int): Int {
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

    companion object {
        private val tokenRegex = "[,:]".toRegex()

        fun transform(columnSize: Int, positionText: String): Int {
            val result: List<String> = positionText.split(tokenRegex)
            val rowIndex = result[0].toIntOrNull()
            val columnIndex = result[1].toIntOrNull()

            requireNotNull(rowIndex) { "입력된 좌표는 숫자 값이어야 합니다" }
            requireNotNull(columnIndex) { "입력된 좌표는 숫자 값이어야 합니다" }

            return rowIndex * columnSize + columnIndex
        }
    }
}
