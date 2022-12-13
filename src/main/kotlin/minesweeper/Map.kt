package minesweeper

class Map(val maxSize: Position, val mines: List<Mine>) {
    fun print(): String {
        var string = ""

        for (height in 1 .. maxSize.height) {
            for (width in 1 .. maxSize.width) {
                string += if (isMine(Position(width, height))) MINE_STRING else SAFE_STRING
                string += " "
            }
            string += "\n"
        }
        return string
    }

    private fun isMine(position: Position): Boolean {
        return mines.any {
            it.position == position
        }
    }

    companion object {
        private const val MINE_STRING = "*"
        private const val SAFE_STRING = "C"
    }
}