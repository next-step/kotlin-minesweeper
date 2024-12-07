package minesweeper.domain

class Row(columns: Int) {

    private val cells: MutableList<Cell> = MutableList(columns) { Land() }

    init {
        require(columns > 0) { "열은 1 이상이어야 합니다." }
    }

    fun countMines(): Int {
        return cells.count { it is Mine }
    }

    fun setMine(col: Int) {
        require(col in 0 until cells.size) { "열의 범위를 벗어났습니다. 입력된 열 = $col" }
        cells[col] = Mine()
    }

    fun getRow(): List<Cell> {
        return cells.toList()
    }
}
