package domain

import inteface.MineCounter
import inteface.MinePlacementStrategy
import inteface.MinePlacer

class MineManager(
    val minePlacementStrategy: MinePlacementStrategy,
    val minePlacer: MinePlacer,
    val mineCounter: MineCounter
)
