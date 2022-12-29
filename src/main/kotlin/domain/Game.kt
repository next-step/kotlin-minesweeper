package domain

import domain.strategy.CellGenerateStrategy

class Game(
    val boardInfo: BoardInfo,
    private val strategy: CellGenerateStrategy
) {
    fun createBoard(): Board {
        val allLocations = Locations(List(boardInfo.getCellSize()) { it })
        val randomLocations = strategy.generate(allLocations, boardInfo.mineCount)
        val blankLocations = allLocations - randomLocations

        val cells = CellGenerator().generate(randomLocations, blankLocations, boardInfo.row)
        return Board(cells)
    }
}
