package minesweeper.domain

@JvmInline
value class Board(val map: MutableMap<Coordinate, Cell>) {

    fun remainMineCount() = map.count { (_, value) -> value == Cell.Mine }

    companion object {
        private fun emptyMap(boardSize: BoardSize) = mutableMapOf<Coordinate, Cell>().also { board ->
            Coordinates.coordinatesInArea(boardSize.height, boardSize.width).map {
                board[it] = Cell.None
            }
        }

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
