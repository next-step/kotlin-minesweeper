package minesweeper.domain

data class MineLocations(private val mineLocations: List<IntArray>) {
    operator fun get(index: Int) = mineLocations[index]

    override fun equals(other: Any?): Boolean {
        if (other !is MineLocations) {
            return false
        }
        val indexedLocation = mineLocations.withIndex()

        return indexedLocation.find { !(it.value contentEquals other.mineLocations[it.index]) } == null
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {

        fun of(mineIndices: List<Int>, height: Int, width: Int): MineLocations {
            require(height > 0 && width > 0) { MinesweeperMap.INVALID_MAP_SIZE_ERROR_MESSAGE }
            require(mineIndices.size <= height * width) { MinesweeperMap.MINE_COUNT_EXCEED_MAP_SIZE_ERROR_MESSAGE }

            val mineLocationMap = mineIndices.groupBy({ it / width }, { it % width })
            val mineLocationList = List(height) {
                (mineLocationMap[it] ?: emptyList()).toIntArray()
            }
            return MineLocations(mineLocationList)
        }
    }
}
