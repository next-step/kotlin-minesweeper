package minesweeper.domain

object RandomPositionStrategy : PositionStrategy {
    override fun setXPosition(width: Width): Int = (0 until width.value).random()
    override fun setYPosition(height: Height): Int = (0 until height.value).random()
}
