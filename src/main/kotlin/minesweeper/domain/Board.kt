package minesweeper.domain

@JvmInline
value class Board(val board: List<List<Cell>>) {

    companion object {
        fun generate(boardSize: BoardSize, mineCount: MineCount): Board {
            val maxCellCount = boardSize.height * boardSize.width
            if (mineCount > maxCellCount) throw IllegalArgumentException()
            return Board(List(boardSize.height) { List(boardSize.width) { Cell.None } })
        }
    }
}
