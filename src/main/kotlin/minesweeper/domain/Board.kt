package minesweeper.domain

@JvmInline
value class Board(val map: MutableMap<Coordinate, Cell>) {

    fun remainMineCount() = map.count { (_, value) -> value == Cell.Mine }

    fun groupByRow(): Map<Int, List<Cell>> =
        map.toList().groupBy(keySelector = { (coordinate, _) -> coordinate.y }) { (_, cell) ->
            cell
        }

    companion object {
        private fun emptyMap(boardSize: BoardSize) = sortedMapOf<Coordinate, Cell>(
            *Coordinates.coordinatesInArea(boardSize.height, boardSize.width).map {
                it to Cell.None
            }.toTypedArray()
        )

        fun generate(boardSize: BoardSize, mineCount: MineCount, mineSpawner: MineSpawner = RandomMineSpawner): Board {
            val maxCellCount = boardSize.height * boardSize.width
            if (mineCount > maxCellCount) throw IllegalArgumentException()

            val minePositions = mineSpawner.spawn(boardSize, mineCount)
            val map = emptyMap(boardSize).also { board ->
                minePositions.forEach { board[it] = Cell.Mine }
            }

            return Board(map)
        }
    }
}
