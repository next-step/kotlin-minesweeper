package domain

import domain.strategy.CellGenerateStrategy

class Game(
    val boardInfo: BoardInfo,
    private val strategy: CellGenerateStrategy
) {
    fun createBoard(): Board {
        val cellGenerator = CellGenerator()

        val allLocations = Locations(List(boardInfo.getCellSize().value) { it })
        val randomLocations = strategy.generate(allLocations, boardInfo.mineCount)
        val blankLocations = allLocations - randomLocations
        val cells = cellGenerator(randomLocations, blankLocations, boardInfo.row)

        return Board(cells)
    }

    fun markMinesAroundCountInBoard(board: Board) {
        board.markMinesAroundCount(boardInfo)
    }
}
