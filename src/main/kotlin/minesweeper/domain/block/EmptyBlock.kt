package minesweeper.domain.block

import minesweeper.domain.block.state.OpenState
import minesweeper.domain.block.state.Opened
import minesweeper.domain.block.state.UnOpened
import minesweeper.domain.board.Board

data class EmptyBlock(
    override val position: Position,
    override val openState: OpenState = UnOpened
) : Block(position, openState) {
    override val isMine: Boolean = false

    override fun adjacentMineCount(board: Board): AdjacentMineCount {
        val minePositions = board.blocks
            .filter { it.isMine }
            .map { it.position }
        return AdjacentMineCount.from(
            MineSearchDirections.values()
                .map { it.nextCoordinate(position.x, position.y) }
                .count { Position(it.first, it.second) in minePositions }
        )
    }

    override fun open(): Block = EmptyBlock(position, Opened)
}
