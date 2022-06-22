package minesweeper.model.coordinate

data class Position(override val row: Int, override val column: Int) : Coordinate {

    val surroundPositions: List<Position> by lazy {
        listOf(
            this.aboveLeft(), this.above(), this.aboveRight(),
            this.left(), this.right(),
            this.bottomLeft(), this.bottom(), this.bottomRight()
        )
    }

    private fun above(): Position = this.copy(row = this.row - 1)
    private fun bottom(): Position = this.copy(row = this.row + 1)
    private fun left(): Position = this.copy(column = this.column - 1)
    private fun right(): Position = this.copy(column = this.column + 1)
    private fun aboveLeft(): Position = this.above().left()
    private fun aboveRight(): Position = this.above().right()
    private fun bottomLeft(): Position = this.bottom().left()
    private fun bottomRight(): Position = this.bottom().right()
}
