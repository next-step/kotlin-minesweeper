package minesweeper.domain

abstract class MineLocationGenerator {
    fun generateLocation(height: Int, width: Int, mineCount: Int): MineLocations {
        require(height > 0 && width > 0) { MinesweeperMap.INVALID_MAP_SIZE_ERROR_MESSAGE }
        require(mineCount <= height * width) { MinesweeperMap.MINE_COUNT_EXCEED_MAP_SIZE_ERROR_MESSAGE }
        val mineIndices = getMineIndices(height, width, mineCount)

        val mineLocationMap = mineIndices.groupBy({ it / width }, { it % width })
        val mineLocationList = List(height) {
            MineLocationRow(mineLocationMap[it] ?: emptyList())
        }
        return MineLocations(mineLocationList, width, height)
    }

    protected abstract fun getMineIndices(height: Int, width: Int, mineCount: Int): MineIndices
}

data class MineIndices(private val indices: List<Int>) : Iterable<Int> by indices
