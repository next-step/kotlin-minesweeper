package minesweeper.domain

class Coordinates(private val coordinateList: List<Coordinate>) {

    operator fun get(index: Int): Coordinate = coordinateList[index]

    companion object {

        @Suppress("FunctionName")
        fun Of(coordinateList: List<Coordinate>): Coordinates {
            return Coordinates(coordinateList)
        }
    }
}
