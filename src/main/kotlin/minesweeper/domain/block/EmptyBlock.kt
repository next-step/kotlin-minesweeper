package minesweeper.domain.block

import minesweeper.domain.Board
import minesweeper.domain.MineSearchDirections
import minesweeper.domain.block.state.OpenState
import minesweeper.domain.block.state.Opened
import minesweeper.domain.block.state.UnOpened

data class EmptyBlock(
    override val position: Position,
    override val openState: OpenState = UnOpened
) : Block(position, openState) {
    override val isMine: Boolean = false

    override fun adjacentMineCount(board: Board): AdjacentMineCount {
        val minePositions = board.blocks
            .filter { it.isMine }
            .map { it.position }
        return AdjacentMineCount.from(MineSearchDirections.values()
            .map { directions -> directions.nextCoordinate(position.x, position.y) }
            .count { Position(it.first, it.second) in minePositions })
    }

    override fun open(): Block = EmptyBlock(position, Opened)
}
