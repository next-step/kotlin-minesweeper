package domain

class BoardInfo(val boardHeight: Int, boardWidth: Int, private val mines: Mines) {

    val boardItems = List(boardHeight) { row ->
        BoardRow(List(boardWidth) { col ->
            BoardItem.getItemType(mines.contains(row, col))
        })
    }
}
