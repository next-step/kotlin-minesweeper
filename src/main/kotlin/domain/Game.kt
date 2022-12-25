package domain

class Game(
    val boardInfo: BoardInfo
) {
    fun createBoard(): Board {
        val allLocations = Locations.from(boardInfo.getCellSize(), List(boardInfo.getCellSize()) { it })
        val randomMineLocations = getRandomLocations()

        val mineCells = MineGenerator(randomMineLocations, boardInfo).generate()
        val blankLocations = allLocations - randomMineLocations
        val board = Board(mineCells)

        val blankCells = BlankGenerator(blankLocations, boardInfo, board).generate()
        board.addAll(blankCells)

        return board
    }

    private fun getRandomLocations(): Locations {
        val list = (0 until boardInfo.getCellSize()).shuffled().take(boardInfo.getMineCount())
        return Locations.from(boardInfo.getCellSize(), list)
    }
}
