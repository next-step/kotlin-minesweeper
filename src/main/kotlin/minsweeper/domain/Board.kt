package minsweeper.domain

class Board private constructor(
    private val boardSize: BoardSize,
    val boardLines: BoardLines,
) {

    fun open(coordinate: Coordinate): Cell {
        require(coordinate.row < boardSize.height && coordinate.column < boardSize.width) {
            BOARD_SIZE_EXCEPTION
        }

        val cell = coordinate.findCell()
            .apply { open() }

        openAround(coordinate)

        return cell
    }

    private fun openAround(coordinate: Coordinate) {
        val cell = coordinate.findCell()
        val island = cell as? Cell.Island ?: return

        if (!island.isAroundMineCountZero()) {
            return
        }

        coordinate.aroundCoordinates(boardSize)
            .filter { !it.findCell().isOpened }
            .forEach { aroundCoordinate ->
                aroundCoordinate.findCell()
                    .open()
                openAround(aroundCoordinate)
            }
    }

    private fun Coordinate.findCell(): Cell = boardLines.lines[row].cells[column]

    companion object {

        private const val BOARD_SIZE_EXCEPTION = "Board 크기를 벗어날 수 없습니다"

        fun of(
            boardSize: BoardSize,
            mineCount: Int,
            boardLinesGenerator: BoardLinesGenerator,
        ): Board = Board(boardSize, boardLinesGenerator.generate(boardSize, mineCount))

        fun of(
            boardSize: BoardSize,
            boardLines: BoardLines,
        ) = Board(boardSize, boardLines)

    }

}
