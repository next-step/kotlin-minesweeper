package next.step.minesweeper.domain.mine

@JvmInline
value class MinePositions(private val positions: Set<MinePosition>) : Set<MinePosition> by positions
