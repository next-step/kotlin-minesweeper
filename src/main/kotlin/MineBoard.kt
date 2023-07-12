data class MineBoard(
    val boardSize: BoardSize,
    val mineCount: Int,
    val boardInfoGenerator: BoardInfoGenerator = BoardInfoGenerator(boardSize, mineCount)
) {
    val info: BoardInfo by lazy {
        boardInfoGenerator.generate()
    }
}