package domain

class Game(
    val boardInfo: BoardInfo
) {
    fun createBoard(): Board {
        val allLocations = List(boardInfo.getCellSize()) { it }
        val randomMineLocations = getRandomLocations()

        val mineCells = MineGenerator(randomMineLocations, boardInfo).generate()
        val blankLocations = allLocations - randomMineLocations.toSet()
        val board = Board(mineCells)

        val blankCells = BlankGenerator(blankLocations, boardInfo, board).generate()
        board.addAll(blankCells)

        return board
    }

    private fun getRandomLocations(): List<Int> {
        return (0 until boardInfo.getCellSize()).shuffled().take(boardInfo.getMineCount())
    }
}
