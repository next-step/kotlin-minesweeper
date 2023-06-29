package tdd.minesweeper.converter

import tdd.minesweeper.domain.Point

val mineSweeperPointConverter = PointConverter { (x, y) -> Point(x - 1, y - 1) }
