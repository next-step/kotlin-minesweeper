package minsweeper

import minsweeper.domain.Cell
import minsweeper.domain.Coordinate

val CELLS = mapOf(
    Coordinate(1, 1) to Cell.Island(aroundMineAmount = 0),
    Coordinate(1, 2) to Cell.Island(aroundMineAmount = 1),
    Coordinate(1, 3) to Cell.Mine(),
    Coordinate(2, 1) to Cell.Island(aroundMineAmount = 0),
    Coordinate(2, 2) to Cell.Island(aroundMineAmount = 1),
    Coordinate(2, 3) to Cell.Island(aroundMineAmount = 1),
    Coordinate(3, 1) to Cell.Island(aroundMineAmount = 0),
    Coordinate(3, 2) to Cell.Island(aroundMineAmount = 0),
    Coordinate(3, 3) to Cell.Island(aroundMineAmount = 0),
)