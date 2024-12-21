package domain

class BoardCreator(
    private val minePlacer: MinePlacer,
) {
    fun create(
        height: Int,
        width: Int,
        mineCount: Int,
    ): Board {
        require(height > 0) { "높이는 0보다 커야 합니다." }
        require(width > 0) { "너비는 0보다 커야 합니다." }
        val cells = Cells.create(height, width)
        return Board.from(cells)
            .placeMines(minePlacer, mineCount)
    }
}
