package model

import model.board.BoardSize

data class Positions(private val positions: Set<Position>) {
    val size: Int
        get() = positions.size

    operator fun contains(position: Position): Boolean {
        return position in positions
    }

    constructor(vararg positions: Position) : this(positions.toSet())

    constructor(positions: List<Position>) : this(positions.toSet())

    companion object {
        fun random(boardSize: BoardSize, size: Int): Positions {
            return Positions(
                List(boardSize.height) { it }.flatMap { height ->
                    List(boardSize.width) { it }.shuffled().map { width ->
                        Position.get(height, width)
                    }
                }.shuffled().take(size)
            )
        }
    }
}
