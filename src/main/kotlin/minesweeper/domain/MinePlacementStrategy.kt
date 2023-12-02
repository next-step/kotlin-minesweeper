package minesweeper.domain

fun interface MinePlacementStrategy {
    fun selectIndices(allCells: MutableList<Cell>, mineCount: Int): List<Int>
}
