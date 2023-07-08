package minesweeper.domain.pin

import minesweeper.domain.pin.state.PinState

sealed class Pin {
    private var state = PinState.CLOSE

    fun open() {
        state = state.toOpen()
    }

    fun isOpenable(): Boolean {
        return state == PinState.CLOSE
    }

    fun isMinePin(): Boolean {
        return this is MinePin
    }
}
