package minesweeper

data class Position(val x: Int, val y: Int) {
    fun key(): Int {
        return hashCode()
    }
}
