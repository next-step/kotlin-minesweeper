package domain

class BoardInfoGenerator(
    private val boardSize: BoardSize,
    private val mineCount: Int,
    private val mineLocationStrategy: MineLocationStrategy = RandomMineLocationStrategy()
) {

    fun generate(): BoardInfo {
        val mineLocations = mineLocationStrategy.generateMineLocations(boardSize, mineCount)
        val layout = mineLocations.layoutWithMines(boardSize)
        return BoardInfo(layout)
    }
}
