package minesweeper.domain

data class MineLocations(private val mineLocations: List<MineLocationRow>) {
    operator fun get(index: Int) = mineLocations[index]
}

data class MineLocationRow(private val row: List<Int>) : Iterable<Int> by row
