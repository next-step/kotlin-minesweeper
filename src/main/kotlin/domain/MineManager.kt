package domain

import inteface.MineCounter
import inteface.MinePlacementStrategy

class MineManager(
    val minePlacementStrategy: MinePlacementStrategy,
    val mineCounter: MineCounter
)
