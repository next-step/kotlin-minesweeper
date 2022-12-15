package minesweeper.domain

class Map(val maxSize: Position, private val mines: List<Mine>) {
    fun print(): String {
        return drawHeight()
    }

    private fun drawHeight(): String {
        return (1..maxSize.height).joinToString("\n") { height ->
            drawWidth(height)
        }
    }

    private fun drawWidth(height: Int): String {
        return (1..maxSize.width).joinToString(" ") { width ->
            if (isMine(Position(width, height))) MINE_STRING else SAFE_STRING
        }
    }

    private fun isMine(position: Position) = mines.any { it.position == position }

    companion object {
        private const val MINE_STRING = "*"
        private const val SAFE_STRING = "C"
    }
}
