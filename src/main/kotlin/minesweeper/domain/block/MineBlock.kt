package minesweeper.domain.block

import minesweeper.domain.block.state.OpenState
import minesweeper.domain.block.state.Opened
import minesweeper.domain.block.state.UnOpened

data class MineBlock(
    override val openState: OpenState = UnOpened
) : Block(openState) {
    override val isMine: Boolean = true

    override fun open(): Block = MineBlock(Opened)
}
