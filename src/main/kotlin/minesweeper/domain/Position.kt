package minesweeper.domain

class Position(rows: Int, columns: Int, val index: Int) {

    val top = makeTop(index, columns)
    val topLeft = makeTopLeft(columns)
    val topRight = makeTopRight(columns)

    val left = makeLeft(index, columns)
    val right = makeRight(index, columns)

    val bottom = makeBottom(index, rows, columns)
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

    private fun makeBottom(index: Int, rows: Int, columns: Int): Int {
        val diff = index + columns
        if (diff > (rows * columns - 1)) return -1
        return diff
    }

    private fun makeRight(index: Int, columns: Int): Int {
        val right = index + 1
        if (right % columns <= 0) return -1
        return right
    }

    private fun makeLeft(index: Int, columns: Int): Int {
        if (index == 0) return -1
        if (index % columns <= 0) return -1
        return index - 1
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

    private fun makeTop(index: Int, columns: Int): Int {
        val diff = index - columns
        if (diff < 0) return -1
        return diff
    }

    override fun toString(): String {
        return """
            Position $index
            |$topLeft|$top|$topRight|
            |$left|$index|$right|
            |$bottomLeft|$bottom|$bottomRight|
        """.trimIndent()
    }

    companion object {
        private val tokenRegex = "[,:]".toRegex()

        fun toPosition(rowSize: Int, columnSize: Int, positionText: String): Position {
            val result: List<String> = positionText.split(tokenRegex)
            val rowIndex = result[0].toIntOrNull()
            val columnIndex = result[1].toIntOrNull()

            requireNotNull(rowIndex) { "입력된 좌표는 숫자 값이어야 합니다" }
            requireNotNull(columnIndex) { "입력된 좌표는 숫자 값이어야 합니다" }

            val index = rowIndex * columnSize + columnIndex
            return Position(rowSize, columnSize, index)
        }
    }
}
