package domain

import domain.model.GameMap
import domain.model.Point

interface MinesweeperAlgorithm {
    fun openTiles(map: GameMap, point: Point)
}
