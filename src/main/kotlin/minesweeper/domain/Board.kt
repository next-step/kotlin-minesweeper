package minesweeper.domain

class Board(area: Area, mineCount: MineCount, cellsGenerator: CellsGenerator = DefaultCellsGenerator()) {
    init {
        val maxCellCount = area.height * area.width
        require(mineCount <= maxCellCount) { "게임판 보다 많은 지뢰는 배치할 수 없습니다." }
    }

    private val cells: Cells = cellsGenerator.generate(area, mineCount)

    fun getAllCell(): List<Cell> = cells

    fun isCompleted(): Boolean = cells.isAllBlockOpened()

    fun openAllMine() {
        cells.openAllMine()
    }

    fun open(coordinate: Coordinate): CellsOpenResult = cells.open(coordinate)
}
