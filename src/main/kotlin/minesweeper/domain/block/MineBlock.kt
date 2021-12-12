package minesweeper.domain.block

import minesweeper.domain.board.Board
import minesweeper.domain.block.state.OpenState
import minesweeper.domain.block.state.Opened
import minesweeper.domain.block.state.UnOpened
import minesweeper.exception.NotCalculateAdjacentMineCountException

data class MineBlock(
    override val position: Position,
    override val openState: OpenState = UnOpened
) : Block(position, openState) {
    override val isMine: Boolean = true

    override fun adjacentMineCount(board: Board): AdjacentMineCount =
        throw NotCalculateAdjacentMineCountException(this::class.java.toString())

    override fun open(): Block = MineBlock(position, Opened)
}
