package minesweeper.domain.block

sealed class Block(open val position: Position) {
    abstract val isMine: Boolean

    abstract val adjacentMineCount: AdjacentMineCount

    companion object {
        fun minesOrCell(position: Position, minePositions: List<Position>): Block {
            if (minePositions.contains(position)) {
                return MineBlock(position)
            }
            return EmptyBlock.of(position, minePositions)
        }
    }
}
