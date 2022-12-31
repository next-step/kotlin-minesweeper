package domain

import domain.strategy.CellGenerateStrategy
import dto.BoardDto

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

    fun play(
        board: Board,
        inputCoordinate: () -> Coordinate,
        printBoard: (BoardInfo, BoardDto) -> Unit
    ): ResultStatus {
        while (!board.isOpenAllBlank) {
            val coordinate = inputCoordinate.invoke()
            if (board.isMineCell(coordinate)) return ResultStatus.LOSE
            findAndOpenAdjacentBlanks(coordinate, board)
            printBoard(boardInfo, BoardDto.from(board))
        }

        board.openAllCells()
        return ResultStatus.WIN
    }

    private fun findAndOpenAdjacentBlanks(coordinate: Coordinate, board: Board) {
        val cell = board.findOrNull(coordinate)
        if (cell is Blank) {
            board.openAdjacentBlanksBy(cell)
        }
    }
}
