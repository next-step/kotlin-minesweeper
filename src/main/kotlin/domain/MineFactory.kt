package domain

class MineFactory(private val randomPositionIdFactory: RandomPositionIdFactory = DefaultRandomPositionIdFactory()) {

    fun createMines(width: Int, height: Int, mineNum: Int): List<Mine> {

        val mines = mutableListOf<Mine>()
        val maxPositionId = width * height - 1

        randomPositionIdFactory.positionIds(mineNum, maxPositionId).map {
            val position = it.position(width)
            mines.add(Mine(position))
        }

        return mines
    }
}
