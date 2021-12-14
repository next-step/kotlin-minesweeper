package minesweeper.domain.block.state

object UnOpened : OpenState() {
    override val isOpened: Boolean = false

    override val open: OpenState = Opened
}
