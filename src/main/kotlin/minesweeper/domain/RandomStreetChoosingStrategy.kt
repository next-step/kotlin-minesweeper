package minesweeper.domain

object RandomStreetChoosingStrategy : StreetChoosingStrategy {
    override fun getStreetNumber(streetCount: Int): Int = (0 until streetCount).random()
}
