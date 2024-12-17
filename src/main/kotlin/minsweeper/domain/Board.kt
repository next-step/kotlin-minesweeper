package minsweeper.domain

class Board private constructor(
    private val boardSize: BoardSize,
    val boardLines: BoardLines,
) {
    fun open(coordinate: Coordinate) {
        require(coordinate.row < boardSize.height && coordinate.column < boardSize.width) {
            BOARD_SIZE_EXCEPTION
        }

        boardLines.lines[coordinate.row].cells[coordinate.column].open()
    }

    companion object {

        private const val BOARD_SIZE_EXCEPTION = "Board 크기를 벗어날 수 없습니다"

        fun of(
            boardSize: BoardSize,
            mineCount: Int,
            boardLinesGenerator: BoardLinesGenerator,
        ): Board = Board(boardSize, boardLinesGenerator.generate(boardSize, mineCount))

    }

}
