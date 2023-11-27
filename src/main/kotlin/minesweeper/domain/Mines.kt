package minesweeper.domain

class Mines(
    private val _mines: Map<Pair<Int, Int>, Mine> = mutableMapOf(),
) {
    val mines: Map<Pair<Int, Int>, Mine>
        get() = _mines

    fun findBy(x: Int, y: Int): Mine? {
        return _mines[Pair(x, y)]
    }

    fun contains(x: Int, y: Int): Boolean {
        return _mines.contains(Pair(x, y))
    }
}
