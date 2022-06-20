package minesweeper.domain

@JvmInline
value class Board(val board: List<List<Cell>>) {

    companion object {
        fun generate(boardSize: BoardSize): Board {
            return Board(List(boardSize.height) { List(boardSize.width) { Cell.None } })
        }
    }
}
