package minesweeper.domain

sealed class Pin {
    fun changeToClosePin(): ClosePin {
        return ClosePin(this)
    }

    fun isOpenable(): Boolean {
        return isClosePin() && !isMinePin()
    }

    fun isClosePin(): Boolean {
        return this is ClosePin
    }

    open fun isMinePin(): Boolean {
        return this is MinePin
    }
}
