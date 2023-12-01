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
        Cell(CellStatus.EMPTY, CardVisibilityState.VISIBLE, point = Point(0, 0)),
        Cell(CellStatus.MINE, point = Point(0, 1)),
        Cell(CellStatus.EMPTY, point = Point(0, 2)),
        Cell(CellStatus.EMPTY, point = Point(0, 3)),
        Cell(CellStatus.EMPTY, point = Point(1, 0)),
        Cell(CellStatus.EMPTY, point = Point(1, 1)),
        Cell(CellStatus.EMPTY, point = Point(1, 2)),
        Cell(CellStatus.EMPTY, point = Point(1, 3)),
        Cell(CellStatus.EMPTY, point = Point(2, 0)),
        Cell(CellStatus.EMPTY, point = Point(2, 1)),
        Cell(CellStatus.EMPTY, point = Point(2, 2)),
        Cell(CellStatus.EMPTY, point = Point(2, 3)),
        Cell(CellStatus.EMPTY, point = Point(3, 0)),
        Cell(CellStatus.EMPTY, point = Point(3, 1)),
        Cell(CellStatus.EMPTY, point = Point(3, 2)),
        Cell(CellStatus.EMPTY, point = Point(3, 3)),
        Cell(CellStatus.EMPTY, point = Point(4, 0)),
        Cell(CellStatus.EMPTY, point = Point(4, 1)),
        Cell(CellStatus.EMPTY, point = Point(4, 2)),
        Cell(CellStatus.MINE, point = Point(4, 3)),
        Cell(CellStatus.EMPTY, point = Point(5, 0)),
        Cell(CellStatus.EMPTY, point = Point(5, 1)),
        Cell(CellStatus.MINE, point = Point(5, 2)),
        Cell(CellStatus.EMPTY, point = Point(5, 3)),
    )
}
