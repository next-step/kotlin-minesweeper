package domain

sealed interface Cell {
    val coordinate: Coordinate

    fun isMineCell(): Boolean {
        return this is MineCell
    }

    data class MineCell(override val coordinate: Coordinate) : Cell

    data class EmptyCell(override val coordinate: Coordinate) : Cell
}
