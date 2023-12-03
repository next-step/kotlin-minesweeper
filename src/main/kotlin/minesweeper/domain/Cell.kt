package minesweeper.domain

sealed interface Cell {
    val openState: OpenState
    fun open(): Cell
}

data class Mine(override val openState: OpenState = OpenState.CLOSED) : Cell {
    override fun open(): Mine {
        return copy(openState = OpenState.OPENED)
    }
}

data class Empty(
    val mineCount: Int = 0,
    override val openState: OpenState = OpenState.CLOSED,
) : Cell {
    override fun open(): Empty {
        return copy(openState = OpenState.OPENED)
    }
}
