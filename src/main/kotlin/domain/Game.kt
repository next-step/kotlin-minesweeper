package domain

import domain.strategy.CellGenerateStrategy

class Game(
    val boardInfo: BoardInfo,
    private val strategy: CellGenerateStrategy,
    var status: GameStatus = INITIAL_GAME_STATUS,
) {
    val isProceeding: Boolean
        get() = status == GameStatus.PROCEEDING

    fun createBoard(): Board {
        val cellGenerator = CellGenerator()

        val allLocations = Locations(List(boardInfo.getCellSize().value) { it })
        val randomLocations = strategy.generate(allLocations, boardInfo.mineCount)
        val blankLocations = allLocations - randomLocations
        val cells = cellGenerator(randomLocations, blankLocations, boardInfo.row)

        val board = Board(cells)
        board.markMinesAroundCount(boardInfo)

        return board
    }

    fun openCell(board: Board, coordinate: Coordinate) {
        if (board.isMineCell(coordinate)) {
            changeStatus(GameStatus.LOSE)
            board.openAllMineCells()
            return
        }

        val blankCell = board.getBlankCell(coordinate)
        board.openAdjacentBlanksBy(blankCell)

        if (board.isOpenAllBlank) {
            changeStatus(GameStatus.WIN)
            board.openRemainCells()
            return
        }
    }

    private fun changeStatus(status: GameStatus) {
        this.status = status
    }

    companion object {
        private val INITIAL_GAME_STATUS = GameStatus.PROCEEDING
    }
}
