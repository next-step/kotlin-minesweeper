package minsweeper.domain

data class Position(
    val row: Int,
    val column: Int,
) {

    fun left(): Position? = this.takeIf { column > 0 }
        ?.copy(column = column - 1)

    fun right(width: Int): Position? = this.takeIf { column < width - 1 }
        ?.copy(column = column + 1)

    fun topLeft(): Position? = this.takeIf { row > 0 && column > 0 }
        ?.copy(row = row - 1, column = column - 1)

    fun topCenter(): Position? = this.takeIf { row > 0 }
        ?.copy(row = row - 1)

    fun topRight(width: Int): Position? = this.takeIf { row > 0 && column < width - 1 }
        ?.copy(row = row - 1, column = column + 1)

    fun bottomLeft(height: Int): Position? = this.takeIf { row < height - 1 && column > 0 }
        ?.copy(row = row + 1, column = column - 1)

    fun bottomCenter(height: Int): Position? = this.takeIf { row < height - 1 }
        ?.copy(row = row + 1)

    fun bottomRight(width: Int, height: Int): Position? =
        this.takeIf { column < width - 1 && row < height - 1 }
            ?.copy(row = row + 1, column = column + 1)

}
