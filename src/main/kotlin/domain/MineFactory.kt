package domain

import model.GameData

class MineFactory(private val positionIdFactory: PositionIdFactory = RandomPositionIdFactory()) {

    fun createMines(gameData: GameData): Mines {

        val maxPositionId = gameData.width * gameData.height - 1

        val mines = positionIdFactory.positionIds(gameData.mineNumber, maxPositionId).map {
            Mine(it.position(gameData.width))
        }

        return Mines(mines)
    }
}
