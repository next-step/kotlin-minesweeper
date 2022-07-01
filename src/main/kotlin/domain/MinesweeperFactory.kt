package domain

private typealias Matrix = Array<Array<Cell>>

abstract class MinesweeperFactory {
    fun create(minesweeperInfo: MinesweeperInfo): Minesweeper {
        return createInitialMatrix(minesweeperInfo).apply {
            setMines(minesweeperInfo)
        }.toMinesweeper()
    }

    private fun createInitialMatrix(minesweeperInfo: MinesweeperInfo): Matrix {
        return Array(minesweeperInfo.rowCount) {
            Array(minesweeperInfo.columnCount) {
                Cell.Land.ZERO
            }
        }
    }

    private fun Matrix.setMines(minesweeperInfo: MinesweeperInfo) {
        val minePositions = getMinePositions(minesweeperInfo)
        require(minePositions.size == minesweeperInfo.mineCount) {
            MESSAGE_INVALID_MINE_COUNT
        }

        getMinePositions(minesweeperInfo).forEach { minePosition ->
            setMine(minePosition)
            increaseMineCountOfNearCells(minePosition, minesweeperInfo)
        }
    }

    protected abstract fun getMinePositions(minesweeperInfo: MinesweeperInfo): List<CellPosition>

    private fun Matrix.setMine(minePosition: CellPosition) {
        this[minePosition.row][minePosition.column] = Cell.Mine
    }

    private fun Matrix.increaseMineCountOfNearCells(minePosition: CellPosition, minesweeperInfo: MinesweeperInfo) {
        Direction.values().forEach { direction ->
            val nearPosition = minePosition.apply(direction)

            if (nearPosition.isValid(minesweeperInfo)) return@forEach
            this[nearPosition.row][nearPosition.column] =
                this[nearPosition.row][nearPosition.column].increaseMineCount()
        }
    }

    private fun Matrix.toMinesweeper(): Minesweeper = Minesweeper(map { row -> Row(*row) })

    companion object {
        private const val MESSAGE_INVALID_MINE_COUNT = "사용자의 입력과 동일한 개수의 지뢰가 생성되어야 합니다."
    }
}
