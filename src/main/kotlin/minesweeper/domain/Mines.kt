package minesweeper.domain

class Mines(
    private val _mines: Map<Position, Mine> = mutableMapOf(),
) {
    val mines: Map<Position, Mine>
        get() = _mines

    fun findBy(x: Int, y: Int): Mine? {
        return _mines[Position(x, y)]
    }

    fun contains(x: Int, y: Int): Boolean {
        return _mines.contains(Position(x, y))
    }
}
