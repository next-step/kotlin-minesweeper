package minesweeper.domain

class BoardMeta(val boardSize: BoardSize, val mineCount: Int) {
    val width: Int
        get() = boardSize.width
    val height: Int
        get() = boardSize.height

    constructor(width: Int, height: Int, mineCount: Int) : this(BoardSize(width, height), mineCount)

    init {
        require(mineCount > 0) { "지뢰 개수는 0보다 커야 합니다." }
        require(mineCount < width * height) { "지뢰 개수는 가로 * 세로 보다 작아야 합니다." }
    }
}
