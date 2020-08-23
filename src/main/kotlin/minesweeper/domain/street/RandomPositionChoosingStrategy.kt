package minesweeper.domain.street

object RandomPositionChoosingStrategy : PositionChoosingStrategy {
    override fun getPosition(streetWidth: Int): Int = (0 until streetWidth).random()
}
