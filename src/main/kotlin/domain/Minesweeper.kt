package domain

class Minesweeper(private val board: List<Row>) : List<Row> by board {
    operator fun get(position: CellPosition): Cell {
        return board[position.row][position.column]
    }

    private operator fun set(position: CellPosition, value: Cell) {
        board[position.row][position.column] = value
    }

    companion object {
        fun from(minesweeperInfo: MinesweeperInfo): Minesweeper {
            val board = List(minesweeperInfo.rowCount) {
                getRow(minesweeperInfo.columnCount)
            }
            return Minesweeper(board)
        }

        private fun getRow(columnCount: Int): Row {
            return Row(columnCount) {
                Cell.Land.ZERO
            }
        }

        fun of(minesweeperInfo: MinesweeperInfo, minePositionsFactory: MinePositionsFactory): Minesweeper {
            return from(minesweeperInfo).apply {
                setMines(minesweeperInfo, minePositionsFactory.create(minesweeperInfo))
            }
        }

        private fun Minesweeper.setMines(minesweeperInfo: MinesweeperInfo, minePositions: List<CellPosition>) {
            minePositions.forEach { minePosition ->
                setMine(minePosition)
                increaseMineCountOfNearCells(minesweeperInfo, minePosition)
            }
        }

        private fun Minesweeper.setMine(minePosition: CellPosition) {
            this[minePosition] = Cell.Mine
        }

        private fun Minesweeper.increaseMineCountOfNearCells(
            minesweeperInfo: MinesweeperInfo,
            minePosition: CellPosition
        ) {
            Direction.values().forEach { direction ->
                val nearPosition = minePosition.moveTo(direction)

                if (!nearPosition.isInBoundaryOf(minesweeperInfo)) return@forEach
                this[nearPosition] = this[nearPosition].increaseMineCount()
            }
        }
    }
}
