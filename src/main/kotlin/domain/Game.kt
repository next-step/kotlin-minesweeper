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
        column: Int,
    ): Boolean {
        return mineField.openCell(row, column)
    }

    fun isWin(): Boolean {
        return mineField.getState().areAllNonMinesOpened()
    }
}
