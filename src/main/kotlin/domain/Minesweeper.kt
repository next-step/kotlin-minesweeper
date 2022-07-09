package domain

class Minesweeper private constructor(
    board: List<Row>,
    private val minesweeperInfo: MinesweeperInfo
) : List<Row> by board {
    operator fun get(position: CellPosition): Cell {
        return this[position.row].value[position.column]
    }

    operator fun set(position: CellPosition, value: Cell) {
        this[position] = value
    }

    fun setMines(minePositions: List<CellPosition>) {
        minePositions.forEach { minePosition ->
            setMine(minePosition)
            increaseMineCountOfNearCells(minePosition)
        }
    }

    private fun setMine(minePosition: CellPosition) {
        this[minePosition.row].value[minePosition.column] = Cell.Mine
    }

    private fun increaseMineCountOfNearCells(minePosition: CellPosition) {
        Direction.values().forEach { direction ->
            val nearPosition = minePosition.apply(direction)

            if (nearPosition.isInBoundaryOf(minesweeperInfo)) return@forEach
            this[nearPosition] = this[nearPosition].increaseMineCount()
        }
    }

    companion object {
        fun from(minesweeperInfo: MinesweeperInfo): Minesweeper {
            val board = List(minesweeperInfo.rowCount) {
                Row.from(minesweeperInfo.columnCount)
            }
            return Minesweeper(board, minesweeperInfo)
        }
    }
}
