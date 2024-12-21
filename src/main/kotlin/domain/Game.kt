package domain

class Game(
    height: Int,
    width: Int,
    mineCount: Int,
) {
    private val mineField =
        MineField(
            Height(height),
            Width(width),
            mineCount,
        )

    fun getMineFieldState(): MineFieldState {
        return mineField.getState()
    }

    fun openCell(
        row: Int,
        col: Int,
    ): Boolean {
        return mineField.openCell(row, col)
    }

    fun isWin(): Boolean {
        val state = mineField.getState()
        return state.cells.getRows().all { row ->
            row.getCells().all { cell ->
                cell.isOpen || cell.isMine()
            }
        }
    }
}
