package minesweeper.domain

class MinesweeperMap(private val height: Int, private val width: Int, private val map: List<MinesweeperMapRow>) : Iterable<MinesweeperMapRow> by map {
    fun open(point: Point): Result<Int> {
        require(point.x in 0 until width && point.y in 0 until height) { INVALID_POINT_ERROR_MESSAGE }
        if (map[point.y].isMine(point.x)) {
            map[point.y].open(point.x)
            return Result.failure(Exception())
        }
        openAdjacent(point)

        return Result.success(getCoveredNumberMapElementCount())
    }

    private fun openAdjacent(point: Point) {
        if (!(point.x in 0 until width && point.y in 0 until height) || !map[point.y].isCovered(point.x) || map[point.y].isMine(point.x)) {
            return
        }
        map[point.y].open(point.x)
        if (map[point.y].getNumber(point.x) != 0) {
            return
        }
        MOVE.map { (xMove, yMove) -> openAdjacent(Point(point.x + xMove, point.y + yMove)) }
    }

    private fun getCoveredNumberMapElementCount(): Int {
        return map.sumOf { row -> row.getCoveredNumberMapElementCount() }
    }

    companion object {
        const val INVALID_MAP_SIZE_ERROR_MESSAGE = "맵의 한변의 길이가 0이하일 수 없습니다"
        const val MINE_COUNT_EXCEED_MAP_SIZE_ERROR_MESSAGE = "지뢰의 개수가 맵의 크기보다 많을 수 없습니다"
        const val INVALID_POINT_ERROR_MESSAGE = "맵의 범위를 벗어난 좌표입니다"
        private val MOVE = listOf(
            Pair(-1, 0),
            Pair(0, -1),
            Pair(0, 1),
            Pair(1, 0),
        )

        fun of(
            height: Int,
            width: Int,
            mineCount: Int,
            mineLocationGenerator: MineLocationGenerator = RandomMineLocationGenerator,
        ): MinesweeperMap {
            require(height > 0 && width > 0) { INVALID_MAP_SIZE_ERROR_MESSAGE }
            require(height * width >= mineCount) { MINE_COUNT_EXCEED_MAP_SIZE_ERROR_MESSAGE }

            val mineLocation = mineLocationGenerator.generateLocation(height, width, mineCount)
            val minesweeperMapRowList = List(height) { rowNumber ->
                MinesweeperMapRow.of(rowNumber, width, mineLocation)
            }
            return MinesweeperMap(height, width, minesweeperMapRowList)
        }
    }
}
