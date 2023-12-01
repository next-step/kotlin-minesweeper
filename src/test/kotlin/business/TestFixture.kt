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
            RowCells(
                Cell(CellStatus.EMPTY, CardVisibilityState.VISIBLE),
                Cell(CellStatus.MINE),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY)
            ),
            RowCells(
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY)
            ),
            RowCells(
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY)
            ),
            RowCells(
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY)
            ),
            RowCells(
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.MINE)
            ),
            RowCells(
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.MINE),
                Cell(CellStatus.EMPTY),
            ),
        )
    )
}
