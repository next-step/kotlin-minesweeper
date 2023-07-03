package next.step.minesweeper.domain.board

@JvmInline
value class BoardPositions(private val positions: Set<BoardPosition>) : Set<BoardPosition> by positions
