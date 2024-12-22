package minsweeper.domain

sealed class Cell(isOpened: Boolean) {
    fun isMine() = this is Mine

    var isOpened: Boolean = isOpened
        private set

    fun open() {
        this.isOpened = true
    }

    class Mine(isOpened: Boolean = false) : Cell(isOpened)

    class Island(
        val aroundMineAmount: Int,
        isOpened: Boolean = false,
    ) : Cell(isOpened) {

        val isAroundMineAmountZero: Boolean
            get() = aroundMineAmount == 0

    }

}