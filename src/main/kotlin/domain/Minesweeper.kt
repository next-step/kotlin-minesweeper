package domain

class Minesweeper(private val board: List<Row>) : List<Row> by board {
    operator fun get(position: CellPosition): Cell {
        return board[position.row][position.column]
    }

    private operator fun set(position: CellPosition, value: Cell) {
        board[position.row][position.column] = value
    }

    companion object {
        fun of(minesweeperInfo: MinesweeperInfo, minePositionsFactory: MinePositionsFactory): Minesweeper {
            fun Minesweeper.setMine(minePosition: CellPosition) {
                this[minePosition] = Cell.Mine
            }

            fun Minesweeper.increaseMineCountOfNearCells(minePosition: CellPosition) {
                Direction.values().forEach { direction ->
                    val nearPosition = minePosition.moveTo(direction)

                    if (!nearPosition.isInBoundaryOf(minesweeperInfo)) return@forEach
                    this[nearPosition] = this[nearPosition].increaseMineCount()
                }
            }

            fun Minesweeper.setMines(minePositions: List<CellPosition>) {
                minePositions.forEach { minePosition ->
                    setMine(minePosition)
                    increaseMineCountOfNearCells(minePosition)
                }
            }

            return from(minesweeperInfo).apply {
                setMines(minePositionsFactory.create(minesweeperInfo))
            }
        }

        fun from(minesweeperInfo: MinesweeperInfo): Minesweeper {
            val board = List(minesweeperInfo.rowCount) {
                Row.from(minesweeperInfo.columnCount)
            }
            return Minesweeper(board)
        }
    }
}
