package minesweeper.domain.position

@JvmInline
value class Positions(private val positions: List<Position>) : List<Position> by positions
