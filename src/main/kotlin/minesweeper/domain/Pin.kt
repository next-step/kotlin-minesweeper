package minesweeper.domain

sealed class Pin {
    fun changeToClosePin(): ClosePin {
        return ClosePin(this)
    }

    open fun isMinePin(): Boolean {
        return this is MinePin
    }
}
