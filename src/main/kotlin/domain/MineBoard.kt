package domain

data class MineBoard(
    val boardSize: BoardSize,
    val mineCount: Int,
    val boardInfoGenerator: BoardInfoGenerator = BoardInfoGenerator(boardSize, mineCount)
) {
    val info: BoardInfo by lazy {
        boardInfoGenerator.generate()
    }
    init {
        require(boardSize.area >= mineCount) {
            "지뢰판의 크기보다 지뢰의 개수가 더 많습니다. [지뢰 개수: $mineCount]"
        }
    }
}
