package next.step.minesweeper.domain.position

@JvmInline
value class Positions(private val positions: Set<Position>) : Set<Position> by positions
