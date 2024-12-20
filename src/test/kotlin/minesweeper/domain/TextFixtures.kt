package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.cell.LandmineCell
import minesweeper.domain.cell.Location
import minesweeper.domain.cell.NumberCell
import minesweeper.domain.cell.NumberOfAdjacentMines

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

val fiveByFiveCellsWithFiveLandmines: List<Cell> =
    listOf(
        ClosedCell(location = oneByOneLocation),
        ClosedCell(location = oneByTwoLocation),
        ClosedCell(location = oneByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(
            location = Location(row = 1, column = 4),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            hasLandmine = true,
        ),
        ClosedCell(location = Location(row = 1, column = 5), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(location = twoByOneLocation),
        ClosedCell(location = twoByTwoLocation),
        ClosedCell(location = twoByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(location = Location(row = 2, column = 4), numberOfAdjacentLandmines = NumberOfAdjacentMines(3)),
        ClosedCell(
            location = Location(row = 2, column = 5),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(2),
            hasLandmine = true,
        ),
        ClosedCell(location = threeByOneLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = threeByTwoLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = threeByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(
            location = Location(row = 3, column = 4),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            hasLandmine = true,
        ),
        ClosedCell(location = Location(row = 3, column = 5), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(
            location = Location(row = 4, column = 1),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            hasLandmine = true,
        ),
        ClosedCell(location = Location(row = 4, column = 2), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(location = Location(row = 4, column = 3), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(location = Location(row = 4, column = 4), numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = Location(row = 4, column = 5), numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = Location(row = 5, column = 1), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(
            location = Location(row = 5, column = 2),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            hasLandmine = true,
        ),
        ClosedCell(location = Location(row = 5, column = 3), numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = Location(row = 5, column = 4)),
        ClosedCell(location = Location(row = 5, column = 5)),
    )

val fiveByFiveCellsWithFiveLandminesAndOneFiveNumberCell: List<Cell> =
    listOf(
        ClosedCell(location = oneByOneLocation),
        ClosedCell(location = oneByTwoLocation),
        ClosedCell(location = oneByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(
            location = Location(row = 1, column = 4),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            hasLandmine = true,
        ),
        NumberCell(location = Location(row = 1, column = 5), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(location = twoByOneLocation),
        ClosedCell(location = twoByTwoLocation),
        ClosedCell(location = twoByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(location = Location(row = 2, column = 4), numberOfAdjacentLandmines = NumberOfAdjacentMines(3)),
        ClosedCell(
            location = Location(row = 2, column = 5),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(2),
            hasLandmine = true,
        ),
        ClosedCell(location = threeByOneLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = threeByTwoLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = threeByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(
            location = Location(row = 3, column = 4),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            hasLandmine = true,
        ),
        ClosedCell(location = Location(row = 3, column = 5), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(
            location = Location(row = 4, column = 1),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            hasLandmine = true,
        ),
        ClosedCell(location = Location(row = 4, column = 2), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(location = Location(row = 4, column = 3), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(location = Location(row = 4, column = 4), numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = Location(row = 4, column = 5), numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = Location(row = 5, column = 1), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(
            location = Location(row = 5, column = 2),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            hasLandmine = true,
        ),
        ClosedCell(location = Location(row = 5, column = 3), numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = Location(row = 5, column = 4)),
        ClosedCell(location = Location(row = 5, column = 5)),
    )

val fiveByFiveCellsWithFiveLandminesAndOneFourLandmineCell: List<Cell> =
    listOf(
        ClosedCell(location = oneByOneLocation),
        ClosedCell(location = oneByTwoLocation),
        ClosedCell(location = oneByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        LandmineCell(location = Location(row = 1, column = 4)),
        ClosedCell(location = Location(row = 1, column = 5), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(location = twoByOneLocation),
        ClosedCell(location = twoByTwoLocation),
        ClosedCell(location = twoByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(location = Location(row = 2, column = 4), numberOfAdjacentLandmines = NumberOfAdjacentMines(3)),
        ClosedCell(
            location = Location(row = 2, column = 5),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(2),
            hasLandmine = true,
        ),
        ClosedCell(location = threeByOneLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = threeByTwoLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = threeByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(
            location = Location(row = 3, column = 4),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            hasLandmine = true,
        ),
        ClosedCell(location = Location(row = 3, column = 5), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(
            location = Location(row = 4, column = 1),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            hasLandmine = true,
        ),
        ClosedCell(location = Location(row = 4, column = 2), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(location = Location(row = 4, column = 3), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(location = Location(row = 4, column = 4), numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = Location(row = 4, column = 5), numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = Location(row = 5, column = 1), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(
            location = Location(row = 5, column = 2),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            hasLandmine = true,
        ),
        ClosedCell(location = Location(row = 5, column = 3), numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = Location(row = 5, column = 4)),
        ClosedCell(location = Location(row = 5, column = 5)),
    )

val fiveByFiveCellsWithFiveLandminesOneOneNumberCellAndAdjacentNumberCell: List<Cell> =
    listOf(
        NumberCell(location = oneByOneLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines.ZERO),
        NumberCell(location = oneByTwoLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines.ZERO),
        NumberCell(location = oneByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(
            location = Location(row = 1, column = 4),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            hasLandmine = true,
        ),
        ClosedCell(location = Location(row = 1, column = 5), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        NumberCell(location = twoByOneLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines.ZERO),
        NumberCell(location = twoByTwoLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines.ZERO),
        NumberCell(location = twoByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(location = Location(row = 2, column = 4), numberOfAdjacentLandmines = NumberOfAdjacentMines(3)),
        ClosedCell(
            location = Location(row = 2, column = 5),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(2),
            hasLandmine = true,
        ),
        NumberCell(location = threeByOneLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        NumberCell(location = threeByTwoLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        NumberCell(location = threeByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(
            location = Location(row = 3, column = 4),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            hasLandmine = true,
        ),
        ClosedCell(location = Location(row = 3, column = 5), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(
            location = Location(row = 4, column = 1),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            hasLandmine = true,
        ),
        ClosedCell(location = Location(row = 4, column = 2), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(location = Location(row = 4, column = 3), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(location = Location(row = 4, column = 4), numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = Location(row = 4, column = 5), numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = Location(row = 5, column = 1), numberOfAdjacentLandmines = NumberOfAdjacentMines(2)),
        ClosedCell(
            location = Location(row = 5, column = 2),
            numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            hasLandmine = true,
        ),
        ClosedCell(location = Location(row = 5, column = 3), numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
        ClosedCell(location = Location(row = 5, column = 4)),
        ClosedCell(location = Location(row = 5, column = 5)),
    )
