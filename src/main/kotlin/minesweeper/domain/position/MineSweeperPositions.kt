package minesweeper.domain.position

@JvmInline
value class MineSweeperPositions(private val positions: List<MineSweeperPosition>) :
    List<MineSweeperPosition> by positions {

    companion object {
        fun create(
            widthPositionRange: IntRange,
            yPosition: Int,
            minePositions: Positions,
        ) = MineSweeperPositions(
            widthPositionRange.map { xPosition ->
                val position = Position(x = xPosition, y = yPosition)
                makeMineSweeperPosition(position = position, minePositions = minePositions)
            },
        )

        private fun makeMineSweeperPosition(position: Position, minePositions: Positions): MineSweeperPosition =
            when (minePositions.contains(position)) {
                true -> MinePosition(position)
                false -> EmptyPosition(position = position, minePositions = minePositions)
            }
    }
}
