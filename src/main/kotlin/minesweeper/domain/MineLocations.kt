package minesweeper.domain

data class MineLocations(private val mineLocations: List<MineLocationRow>) {
    operator fun get(index: Int) = mineLocations[index]

    companion object {

        fun of(mineIndices: List<Int>, height: Int, width: Int): MineLocations {
            require(height > 0 && width > 0) { MinesweeperMap.INVALID_MAP_SIZE_ERROR_MESSAGE }
            require(mineIndices.size <= height * width) { MinesweeperMap.MINE_COUNT_EXCEED_MAP_SIZE_ERROR_MESSAGE }

            val mineLocationMap = mineIndices.groupBy({ it / width }, { it % width })
            val mineLocationList = List(height) {
                MineLocationRow(mineLocationMap[it] ?: emptyList())
            }
            return MineLocations(mineLocationList)
        }
    }
}

data class MineLocationRow(private val row: List<Int>) : Iterable<Int> by row
