package business

object TestFixture {

    /**
     *         0   1   2   3
     *
     *  0    | 1 | * | C | C |
     *  1    | C | C | C | C |
     *  2    | C | C | C | C |
     *  3    | C | C | C | C |
     *  4    | C | C | C | * |
     *  5    | C | C | * | C |
     */
    fun testCells() = Cells(
        listOf(
            listOf(
                Cell(CellStatus.EMPTY, CardVisibilityState.VISIBLE),
                Cell(CellStatus.MINE),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY)
            ),
            listOf(
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY)
            ),
            listOf(
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY)
            ),
            listOf(
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY)
            ),
            listOf(
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.MINE)
            ),
            listOf(
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.MINE),
                Cell(CellStatus.EMPTY),
            ),
        )
    )
}
