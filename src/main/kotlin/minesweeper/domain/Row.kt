package minesweeper.domain

class Row(columns: Int) {

    private val _cells: MutableList<Cell> = MutableList(columns) { Land() }

    val cells: List<Cell>
        get() = _cells

    init {
        require(columns > 0) { "열은 1 이상이어야 합니다." }
    }

    fun countMines(): Int {
        return _cells.count { it is Mine }
    }

    fun setMine(col: Int) {
        require(col in 0 until _cells.size) { "열의 범위를 벗어났습니다. 입력된 열 = $col" }
        _cells[col] = Mine()
    }
}
