package minesweeper.domain

class MinesweeperMap(private val map: List<MinesweeperMapRow>) : Iterable<MinesweeperMapRow> by map {

    companion object {
        const val INVALID_MAP_SIZE_ERROR_MESSAGE = "맵의 한변의 길이가 0이하일 수 없습니다"
        const val MINE_COUNT_EXCEED_MAP_SIZE_ERROR_MESSAGE = "지뢰의 개수가 맵의 크기보다 많을 수 없습니다"

        fun of(
            height: Int,
            width: Int,
            mineCount: Int,
            mineLocationGenerator: MineLocationGenerator = RandomMineLocationGenerator,
        ): MinesweeperMap {
            require(height > 0 && width > 0) { INVALID_MAP_SIZE_ERROR_MESSAGE }
            require(height * width >= mineCount) { MINE_COUNT_EXCEED_MAP_SIZE_ERROR_MESSAGE }

            val mineLocation = mineLocationGenerator.generateLocation(height, width, mineCount)
            val minesweeperMapRowList = List(height) {
                MinesweeperMapRow.of(mineLocation[it], width)
            }
            return MinesweeperMap(minesweeperMapRowList)
        }
    }
}
