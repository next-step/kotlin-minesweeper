package minesweeper.domain

class MineBoard(
    private val boardInfo: List<BoardRow>
) {
    fun getCell(point: Point): Cell {
        return boardInfo[point.row].rowInfo[point.col]
    }

    val getBoardInfo: List<BoardRow>
        get() = boardInfo

    val getHeight: Int
        get() = boardInfo.size
    val getWidth: Int
        get() = boardInfo[0].rowInfo.size
}
