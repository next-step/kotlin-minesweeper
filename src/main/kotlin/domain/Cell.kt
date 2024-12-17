package domain

sealed interface Cell {
    fun create(): Cell

    data class MineCell(private val coordinate: Coordinate) : Cell {
        override fun create(): Cell {
            TODO("Not yet implemented")
        }
    }

    data class EmptyCell(private val coordinate: Coordinate) : Cell {
        override fun create(): Cell {
            TODO("Not yet implemented")
        }
    }
}
