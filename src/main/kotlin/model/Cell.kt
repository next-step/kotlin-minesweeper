package model

import dto.CellDTO

data class Cell(
    private val cellType: CellType,
    private val position: Position,
    private var state: State
) {
    constructor(cellType: CellType, position: Position) : this(cellType, position, State.CLOSE)

    fun open() {
        state = State.OPEN
    }

    fun aroundPositions(): List<Position> {
        return position.aroundPositions()
    }

    fun closed(): Boolean {
        return state.closed()
    }

    fun safe(): Boolean {
        return cellType.safe()
    }

    fun exploded(): Boolean {
        return opened() && !safe()
    }

    fun opened(): Boolean {
        return !closed()
    }

    fun asDTO(): CellDTO {
        return CellDTO(
            cellType, position, state
        )
    }
}