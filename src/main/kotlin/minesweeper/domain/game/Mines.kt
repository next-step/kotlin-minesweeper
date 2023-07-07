package minesweeper.domain.game

data class Mines(private val coordinates: List<Coordinate>) : List<Coordinate> by coordinates {
    fun nearMineCount(coordinate: Coordinate): Int = MineAround.values().map {
        Coordinate(coordinate.x + it.x, coordinate.y + it.y)
    }.count {
        coordinates.contains(it)
    }
}

fun List<Coordinate>.toMines() = Mines(this)
