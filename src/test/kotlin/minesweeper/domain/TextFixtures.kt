package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.cell.Location

val oneByOneLocation = Location(row = 1, column = 1)
val oneByTwoLocation = Location(row = 1, column = 2)
val oneByThreeLocation = Location(row = 1, column = 3)

val twoByOneLocation = Location(row = 2, column = 1)
val twoByTwoLocation = Location(row = 2, column = 2)
val twoByThreeLocation = Location(row = 2, column = 3)

val threeByOneLocation = Location(row = 3, column = 1)
val threeByTwoLocation = Location(row = 3, column = 2)
val threeByThreeLocation = Location(row = 3, column = 3)

val threeByThreeCells: List<Cell> =
    listOf(
        ClosedCell(oneByOneLocation),
        ClosedCell(oneByTwoLocation),
        ClosedCell(oneByThreeLocation),
        ClosedCell(twoByOneLocation),
        ClosedCell(twoByTwoLocation),
        ClosedCell(twoByThreeLocation),
        ClosedCell(threeByOneLocation),
        ClosedCell(threeByTwoLocation),
        ClosedCell(threeByThreeLocation),
    )
