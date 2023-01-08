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

        val allLocations = Locations(List(boardInfo.getCellSize().value) { it })
        val randomLocations = strategy.generate(allLocations, boardInfo.mineCount)
        val blankLocations = allLocations - randomLocations
        val cells = CellGenerator(randomLocations, blankLocations, boardInfo.row).generate()

        val board = Board(cells)
        board.markMinesAroundCount(boardInfo)

        return board
    }

    fun openBlankCell(board: Board, blank: Blank) {
        board.openAdjacentBlanksBy(blank)
    }

    fun changeStatus(status: GameStatus) {
        this.status = status
    }

    companion object {
        private val INITIAL_GAME_STATUS = GameStatus.PROCEEDING
    }
}
