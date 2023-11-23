class GameBoard(val cellMatrix: List<CellGrid>) {

    companion object {
        fun of(height: Int, width: Int): GameBoard = GameBoard((0 until height).map { CellGrid.of(width) })
    }
}
