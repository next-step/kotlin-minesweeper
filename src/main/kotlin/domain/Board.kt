package domain

class Board(val boardHeight: Int, boardWidth: Int, private val mines: Mines) {

    val rows = BoardRows(
        List(boardHeight) { row ->
            BoardRow(row, boardWidth, mines)
        }
    )
}
