package minesweeper.domain

class MineBoard(
    val boardInfo: List<BoardRow>,
) {
    val height: Int
        get() = boardInfo.size

    val width: Int
        get() = boardInfo[0].rowInfo.size
}
