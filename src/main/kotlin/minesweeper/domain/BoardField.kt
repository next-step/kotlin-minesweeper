package minesweeper.domain

sealed class BoardField(
    val coordinate: Coordinate,
    isOpen: Boolean,
) {
    var isOpen = isOpen
        private set

    fun open() {
        isOpen = true
    }

    companion object {
        fun nonMine(coordinate: Coordinate): BoardField {
            return NumberField(coordinate)
        }

        fun mine(coordinate: Coordinate): BoardField {
            return MineField(coordinate)
        }
    }
}

class MineField(
    coordinate: Coordinate,
    isOpen: Boolean = false
) : BoardField(coordinate, isOpen) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

class NumberField(
    coordinate: Coordinate,
    isOpen: Boolean = false
) : BoardField(coordinate, isOpen) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    fun number(boardFields: BoardFields): Int {
        val aroundFields = boardFields.aroundFields(coordinate)
        return aroundFields.mineCount()
    }
}
