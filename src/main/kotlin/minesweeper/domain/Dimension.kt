package minesweeper.domain

data class Dimension(val width: PositiveNumber, val height: PositiveNumber) {
    fun size(): PositiveNumber = width * height
    fun allCoordinate(): List<Coordinate> {
        return (1..height.number).flatMap { row ->
            (1..width.number).map { column ->
                Coordinate(row, column)
            }
        }
    }
}
