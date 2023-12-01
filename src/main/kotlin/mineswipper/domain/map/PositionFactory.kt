package mineswipper.domain.map

object PositionFactory {

    fun generateMinePositions(height: Int, width: Int, mineAmount: Int): List<Position> {
        return (0 until  mineAmount).map {
            val x = (0 until height).random()
            val y = (0 until width).random()
            Position(x, y)
        }
    }
}
