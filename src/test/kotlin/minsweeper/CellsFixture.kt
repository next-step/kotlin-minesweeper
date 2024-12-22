package minsweeper

import minsweeper.domain.Cell
import minsweeper.domain.Coordinate

val CELLS: Map<Coordinate, Cell>
    get() = mapOf(
        Coordinate.of(1, 1) to Cell.Island(aroundMineAmount = 0),
        Coordinate.of(1, 2) to Cell.Island(aroundMineAmount = 1),
        Coordinate.of(1, 3) to Cell.Mine(),
        Coordinate.of(2, 1) to Cell.Island(aroundMineAmount = 0),
        Coordinate.of(2, 2) to Cell.Island(aroundMineAmount = 1),
        Coordinate.of(2, 3) to Cell.Island(aroundMineAmount = 1),
        Coordinate.of(3, 1) to Cell.Island(aroundMineAmount = 0),
        Coordinate.of(3, 2) to Cell.Island(aroundMineAmount = 0),
        Coordinate.of(3, 3) to Cell.Island(aroundMineAmount = 0),
    )