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

            if (isOutOfBoundary(nearPosition)) return@forEach
            this[nearPosition] = this[nearPosition].increaseMineCount()
        }
    }

    private fun isOutOfBoundary(cellPosition: CellPosition): Boolean {
        return cellPosition.row < 0 ||
            cellPosition.row >= minesweeperInfo.rowCount ||
            cellPosition.column < 0 ||
            cellPosition.column >= minesweeperInfo.columnCount
    }

    companion object {
        const val MESSAGE_INVALID_MINE_COUNT = "사용자의 입력과 동일한 개수의 지뢰가 생성되어야 합니다."

        fun from(minesweeperInfo: MinesweeperInfo): Minesweeper {
            val board = List(minesweeperInfo.rowCount) {
                Row.from(minesweeperInfo.columnCount)
            }
            return Minesweeper(board, minesweeperInfo)
        }
    }
}
