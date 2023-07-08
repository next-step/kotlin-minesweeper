package minesweeper.domain

class MineBoard(
    private val boardInfo: List<BoardRow>
) {
    fun getCell(point: Point): Cell {
        return boardInfo[point.row].rowInfo[point.col]
    }

    val getBoardInfo: List<BoardRow>
        get() = boardInfo

    val height: Int
        get() = boardInfo.size

    val width: Int
        get() = boardInfo[0].rowInfo.size
}
