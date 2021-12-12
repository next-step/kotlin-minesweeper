package minesweeper.domain.block

import minesweeper.domain.board.Board
import minesweeper.domain.block.state.OpenState

sealed class Block(open val position: Position, open val openState: OpenState) {
    abstract val isMine: Boolean

    abstract fun adjacentMineCount(board: Board): AdjacentMineCount

    abstract fun open(): Block

    fun isOpened(): Boolean = openState.isOpened

    companion object {
        fun minesOrCell(position: Position, minePositions: List<Position>): Block {
            if (minePositions.contains(position)) {
                return MineBlock(position)
            }
            return EmptyBlock(position)
        }
    }
}
