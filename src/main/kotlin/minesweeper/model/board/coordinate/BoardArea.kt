package minesweeper.model.board.coordinate

data class BoardArea(override val rowCount: PositiveInt, override val columnCount: PositiveInt) : Area {

    companion object {
        fun of(rowCount: Int, columnCount: Int) = BoardArea(
            rowCount = PositiveInt(rowCount),
            columnCount = PositiveInt(columnCount)
        )
    }
}
