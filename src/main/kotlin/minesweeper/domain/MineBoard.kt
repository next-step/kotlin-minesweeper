package minesweeper.domain

class MineBoard(
    val boardInfo: List<BoardRow>
) {
    fun getCell(row: Int, col: Int): Cell {
        return boardInfo[row].rowInfo[col]
    }
}
