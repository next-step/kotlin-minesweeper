package minesweeper.domain.block.state

object UnOpened : OpenState() {
    override val isOpened: Boolean = true

    override val open: OpenState = Opened
}
