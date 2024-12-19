package domain

class BoardCreator(
    private val minePlacer: MinePlacer,
) {
    fun create(
        height: Int,
        width: Int,
        mineCount: Int,
    ): Board {
        val cells = Cells.create(height, width)
        return Board.from(cells)
            .placeMines(minePlacer, mineCount)
    }
}
