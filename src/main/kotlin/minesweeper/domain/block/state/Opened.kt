package minesweeper.domain.block.state

object Opened : OpenState() {
    override val isOpened: Boolean = true

    override val open: OpenState get() = throw IllegalArgumentException()
}
