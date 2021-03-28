package domain

import model.GameData

class MineFactory(private val randomPositionIdFactory: RandomPositionIdFactory = DefaultRandomPositionIdFactory()) {

    fun createMines(gameData: GameData): Mines {

        val mines = mutableListOf<Mine>()
        val maxPositionId = gameData.width * gameData.height - 1

        randomPositionIdFactory.positionIds(gameData.mineNumber, maxPositionId).map {
            val position = it.position(gameData.width)
            mines.add(Mine(position))
        }

        return Mines(mines)
    }
}
