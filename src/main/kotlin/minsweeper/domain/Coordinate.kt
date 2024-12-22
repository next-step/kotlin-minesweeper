package minsweeper.domain

data class Coordinate(
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

    private fun left(): Coordinate? = this.takeIf { column > 0 }
        ?.copy(column = column - 1)

    private fun right(width: Int): Coordinate? = this.takeIf { column < width - 1 }
        ?.copy(column = column + 1)

    private fun topLeft(): Coordinate? = this.takeIf { row > 0 && column > 0 }
        ?.copy(row = row - 1, column = column - 1)

    private fun topCenter(): Coordinate? = this.takeIf { row > 0 }
        ?.copy(row = row - 1)

    private fun topRight(width: Int): Coordinate? = this.takeIf { row > 0 && column < width - 1 }
        ?.copy(row = row - 1, column = column + 1)

    private fun bottomLeft(height: Int): Coordinate? = this.takeIf { row < height - 1 && column > 0 }
        ?.copy(row = row + 1, column = column - 1)

    private fun bottomCenter(height: Int): Coordinate? = this.takeIf { row < height - 1 }
        ?.copy(row = row + 1)

    private fun bottomRight(width: Int, height: Int): Coordinate? =
        this.takeIf { column < width - 1 && row < height - 1 }
            ?.copy(row = row + 1, column = column + 1)

    companion object {
        private const val COORDINATE_GREATER_THAN_ZERO = "좌표는 0보다 커야 합니다"
    }

}