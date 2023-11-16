package minesweeper.domain

@JvmInline
value class Mines(val mines: List<Mine>) {
    fun count(): Int {
        return mines.size
    }

    fun isMine(position: Position): Boolean {
        return mines.any { it.position.match(position) }
    }
}
