package domain

data class Board(
    val rows: List<Row>,
) {

    companion object {
        fun create(boardSize: BoardSize, mineCount: MineCount): Board {
            return Board(
                MinePlanter.plantMines(
                    emptySpaces(boardSize),
                    mineCount
                ).chunked(boardSize.width.value)
            )
        }

        private fun emptySpaces(boardSize: BoardSize): Row {
            return Row.emptyRow(boardSize.height.value * boardSize.width.value)
        }
    }
}
