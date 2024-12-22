package minsweeper.domain

import minsweeper.domain.board.BoardSize

data class Coordinate private constructor(
    val row: Int,
    val column: Int,
) {

    init {
        require(row > 0 && column > 0) { COORDINATE_GREATER_THAN_ZERO }
    }

    fun aroundCoordinates(boardSize: BoardSize): List<Coordinate> = listOfNotNull(
        left(),
        right(boardSize.width),
        topLeft(),
        topCenter(),
        topRight(boardSize.width),
        bottomLeft(boardSize.height),
        bottomCenter(boardSize.height),
        bottomRight(boardSize.width, boardSize.height),
    )

    private fun left(): Coordinate? = this.takeIf { column > 1 }
        ?.copy(column = column - 1)

    private fun right(width: Int): Coordinate? = this.takeIf { column < width }
        ?.copy(column = column + 1)

    private fun topLeft(): Coordinate? = this.takeIf { row > 1 && column > 1 }
        ?.copy(row = row - 1, column = column - 1)

    private fun topCenter(): Coordinate? = this.takeIf { row > 1 }
        ?.copy(row = row - 1)

    private fun topRight(width: Int): Coordinate? = this.takeIf { row > 1 && column < width }
        ?.copy(row = row - 1, column = column + 1)

    private fun bottomLeft(height: Int): Coordinate? = this.takeIf { row < height && column > 1 }
        ?.copy(row = row + 1, column = column - 1)

    private fun bottomCenter(height: Int): Coordinate? = this.takeIf { row < height }
        ?.copy(row = row + 1)

    private fun bottomRight(width: Int, height: Int): Coordinate? = this.takeIf { column < width && row < height }
        ?.copy(row = row + 1, column = column + 1)

    companion object {
        private const val COORDINATE_GREATER_THAN_ZERO = "좌표는 0보다 커야 합니다"
        private const val INVALID_INPUT_STRING = "입력값이 올바르지 않습니다"

        fun of(row: Int, column: Int): Coordinate = Coordinate(row, column)

        fun of(input: String): Coordinate {
            val splitInput = input.split(",")
                .map(String::trim)
                .mapNotNull { it.toIntOrNull() }

            require(splitInput.size == 2) { INVALID_INPUT_STRING }
            return of(splitInput[0], splitInput[1])
        }
    }

}