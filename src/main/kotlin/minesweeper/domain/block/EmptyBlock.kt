package minesweeper.domain.block

import minesweeper.domain.block.state.OpenState
import minesweeper.domain.block.state.Opened
import minesweeper.domain.block.state.UnOpened

data class EmptyBlock(
    private val openState: OpenState = UnOpened
) : Block(openState) {
    override val isMine: Boolean = false

    override fun open(): Block = EmptyBlock(Opened)
}
