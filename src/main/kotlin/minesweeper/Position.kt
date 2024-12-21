package minesweeper

data class Position(val x: Int, val y: Int) {
    fun key(): Int {
        return hashCode()
    }

    fun neighbors(): List<Position> {
        return Direction.neighbors(this)
    }

    companion object {
        fun create(readOpenPosition: Pair<Int, Int>): Position {
            return Position(readOpenPosition.first, readOpenPosition.second)
        }
    }
}
