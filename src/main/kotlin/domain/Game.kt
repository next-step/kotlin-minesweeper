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
}
