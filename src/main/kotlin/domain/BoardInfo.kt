package domain

class BoardInfo(val boardHeight: Int, boardWidth: Int, private val mines: Mines) {

    val boardItems = BoardRows(List(boardHeight) { row ->
        BoardRow(row, boardWidth, mines)
    })
}
