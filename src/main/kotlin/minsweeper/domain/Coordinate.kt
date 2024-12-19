package minsweeper.domain

data class Coordinate private constructor(
    val row: Int,
    val column: Int,
) {

    init {
        require(row >= 0 && column >= 0) { NEGATIVE_EXCEPTION }
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

    fun left(): Coordinate? = this.takeIf { column > 0 }
        ?.copy(column = column - 1)

    fun right(width: Int): Coordinate? = this.takeIf { column < width - 1 }
        ?.copy(column = column + 1)

    fun topLeft(): Coordinate? = this.takeIf { row > 0 && column > 0 }
        ?.copy(row = row - 1, column = column - 1)

    fun topCenter(): Coordinate? = this.takeIf { row > 0 }
        ?.copy(row = row - 1)

    fun topRight(width: Int): Coordinate? = this.takeIf { row > 0 && column < width - 1 }
        ?.copy(row = row - 1, column = column + 1)

    fun bottomLeft(height: Int): Coordinate? = this.takeIf { row < height - 1 && column > 0 }
        ?.copy(row = row + 1, column = column - 1)

    fun bottomCenter(height: Int): Coordinate? = this.takeIf { row < height - 1 }
        ?.copy(row = row + 1)

    fun bottomRight(width: Int, height: Int): Coordinate? = this.takeIf { column < width - 1 && row < height - 1 }
        ?.copy(row = row + 1, column = column + 1)

    companion object {
        private const val NEGATIVE_EXCEPTION = "좌표는 음수일 수 없습니다"
        private const val INPUT_STRING_EXCEPTION = "올바른 형식이 아닙니다"

        fun of(
            row: Int,
            column: Int,
        ): Coordinate = Coordinate(row, column)

        fun of(input: String): Coordinate {
            val splitInput = input.split(",")
                .map(String::trim)
                .mapNotNull { it.toIntOrNull()?.minus(1) }

            require(splitInput.size == 2) { INPUT_STRING_EXCEPTION }
            return Coordinate(splitInput[0], splitInput[1])
        }
    }

}
