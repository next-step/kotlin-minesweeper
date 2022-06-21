package minesweeper.domain

data class Coordinates(private val list: List<Coordinate>) : List<Coordinate> by list{

    companion object {
        fun coordinatesInArea(height: Int, width: Int): Coordinates =
            Coordinates(
                (0 until height).map { y ->
                    (0 until width).map { x ->
                        Coordinate(y, x)
                    }
                }.flatten()
            )
    }
}
