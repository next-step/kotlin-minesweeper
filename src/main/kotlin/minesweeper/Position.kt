package minesweeper

data class Position(val x: Int, val y: Int) {
    fun key(): Int {
        return hashCode()
    }

    companion object {
        fun create(readOpenPosition: Pair<Int, Int>): Position {
            return Position(readOpenPosition.first, readOpenPosition.second)
        }
    }
}
