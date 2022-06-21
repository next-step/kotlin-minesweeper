package minesweeper.domain

@JvmInline
value class Board(val map: MutableMap<Coordinate, Cell>) {

    fun remainMineCount() = map.count { (_, value) -> value == Cell.Mine }

    fun groupByColumn(): Map<Int, List<Cell>> =
        map.toList().groupBy(keySelector = { (coordinate, _) -> coordinate.y }) { (_, cell) ->
            cell
        }

    companion object {
        fun generate(boardSize: BoardSize, mineCount: MineCount, mineSpawner: MineSpawner = RandomMineSpawner): Board {
            validateMineCount(boardSize, mineCount)

            val mineCoordinates = mineSpawner.spawn(boardSize, mineCount)
            val map = emptyBoardMap(boardSize)

            mineCoordinates.forEach { map[it] = Cell.Mine }

            return Board(map)
        }

        private fun validateMineCount(boardSize: BoardSize, mineCount: MineCount) {
            val maxCellCount = boardSize.height * boardSize.width
            require(mineCount <= maxCellCount) { "게임판 보다 많은 지뢰는 배치할 수 없습니다." }
        }

        private fun emptyBoardMap(boardSize: BoardSize) = sortedMapOf<Coordinate, Cell>(
            *Coordinates.coordinatesInArea(boardSize.height, boardSize.width).map {
                it to Cell.None
            }.toTypedArray()
        )
    }
}
