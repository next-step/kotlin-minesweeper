package domain

class Game(
    private val row: Row,
    val column: Column,
    private val mineCount: MineCount
) {
    fun createBoard(): Board {
        return Board.from(row, column, mineCount)
    }
}
