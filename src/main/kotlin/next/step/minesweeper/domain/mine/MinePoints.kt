package next.step.minesweeper.domain.mine

import next.step.minesweeper.domain.position.Position

@JvmInline
value class MinePoints(private val positions: Set<Position>) : Set<Position> by positions
