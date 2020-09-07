package minesweeper.domain.square

import minesweeper.domain.squarestate.Empty
import minesweeper.domain.squarestate.SquareState

data class Square(
    val position: Position,
    private val state: SquareState
) {
    constructor(x: Int, y: Int) : this(Position(x, y), state = Empty())

    constructor(x: Int, y: Int, state: SquareState) : this(Position(x, y), state)

    fun isOnBoundary(height: Int, width: Int): Boolean = position.isBoundary(height, width)

    fun hasSamePosition(position: Position): Boolean = this.position == position

    fun isMine(): Boolean = this.state.isMine

    fun isBoundary(): Boolean = this.state.isBoundary()

    fun allAroundPositions(): List<Position> =
        Direction.values().map { direction -> position + direction }.toList()

    fun updateMineCount(count: Int): Square = this.copy(state = state.updateMineCount(count))

    fun currentState(): String = state.toString()
}
