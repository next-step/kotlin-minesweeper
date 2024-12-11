object BasicCellGridTextFixture {
    val threeByThreeGrid: List<List<Cell>> = List(3) { row -> List(3) { column -> BasicCell(row = row + 1, column = column + 1) } }
}
