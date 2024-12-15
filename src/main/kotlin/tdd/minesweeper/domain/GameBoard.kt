package tdd.minesweeper.domain

class GameBoard(
    height: Int,
    width: Int,
    mineCount: Int,
) {
    private val cells = Cells(height, width, mineCount)

    fun getRemainingMineCount(): Int {
        return cells.getActiveMineCount()
    }
}
