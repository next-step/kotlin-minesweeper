package minesweeper

class Mines(
    private val mines: MutableMap<Pair<Int, Int>, Mine> = mutableMapOf(),
) {

    fun add(mine: Mine) {
        val x = mine.x
        val y = mine.y
        mines[Pair(x, y)] = mine
    }

    fun findBy(x: Int, y: Int): Mine? {
        return mines[Pair(x, y)]
    }
}
