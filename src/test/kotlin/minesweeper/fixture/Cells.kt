package minesweeper.fixture

import minesweeper.model.cell.CellGenerator
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

fun CellBuilder(area: Area, mineCellLocator: MineLocator) =
    object : CellGenerator(area) {
        override fun isMineAt(coordinate: Coordinate, firstClickCoordinate: Coordinate) =
            mineCellLocator.invoke(coordinate, firstClickCoordinate)
    }
