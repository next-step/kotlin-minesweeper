package minesweeper.domain.block.state

sealed class OpenState {
    abstract val isOpened: Boolean

    abstract val open: OpenState
}
