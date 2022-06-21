package minesweeper.domain

sealed interface BoardField {
    val coordinate: Coordinate

    companion object {
        fun nonMine(coordinate: Coordinate): BoardField {
            return NumberField(coordinate)
        }

        fun mine(coordinate: Coordinate): BoardField {
            return MineField(coordinate)
        }
    }
}

data class MineField(override val coordinate: Coordinate) : BoardField

data class NumberField(override val coordinate: Coordinate) : BoardField {

    fun number(boardFields: BoardFields): Int {
        val nearFields = boardFields.nearFields(coordinate)
        return nearFields.mineCount()
    }
}
