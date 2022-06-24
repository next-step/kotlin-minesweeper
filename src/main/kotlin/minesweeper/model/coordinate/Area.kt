package minesweeper.model.coordinate

interface Area : Iterable<Coordinate> {

    val columnCount: PositiveInt
    val rowCount: PositiveInt
    val cellCount: Int
    val rowRange: IntRange
    val columnRange: IntRange

    operator fun get(index: Int): Coordinate
    fun surroundCoordinatesOf(coordinate: Coordinate): List<Coordinate>
}
