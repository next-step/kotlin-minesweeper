package domain

sealed class CoordinateType {
    abstract val coordinate: Coordinate
    val x: Column
        get() = coordinate.x
    val y: Row
        get() = coordinate.y

    data class Mine(override val coordinate: Coordinate) : CoordinateType()
    data class Blank(override val coordinate: Coordinate) : CoordinateType()
}
