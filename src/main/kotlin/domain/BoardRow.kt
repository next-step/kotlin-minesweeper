package domain

class BoardRow(height: Int, width: Int, mines: Mines) {
    val boardRow = List(width) { col ->
        BoardItem.getItemType(mines.contains(height, col))
    }
}
