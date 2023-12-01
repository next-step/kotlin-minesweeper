package domain

interface MinePlacer {
    fun placeMines(board: Board, minePositions: List<Position>)
}
