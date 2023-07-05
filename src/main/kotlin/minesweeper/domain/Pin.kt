package minesweeper.domain

sealed class Pin {
    fun changeToClosePin(): ClosePin {
        return ClosePin(this)
    }
}
