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

        val board = Board(cells)
        board.markMinesAroundCount(boardInfo)

        return board
    }

    fun getBlankCell(coordinate: Coordinate, board: Board): Blank {
        val cell = board.findOrNull(coordinate)

        return if (cell is Blank) cell else throw IllegalArgumentException(ERROR_MESSAGE_NOT_EXIST_COORDINATE)
    }

    companion object {
        private const val ERROR_MESSAGE_NOT_EXIST_COORDINATE = "존재하지 않는 좌표입니다."
    }
}
