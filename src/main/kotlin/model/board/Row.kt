package model.board

class Row(cells: List<Cell>) {
    val cells = cells.toList()

    val width: Int
        get() = cells.size

    init {
        require(cells.isNotEmpty()) { "빈 cells 로는 Row 를 생성 할 수 없습니다!" }
    }

    constructor(vararg cells: Cell) : this(cells.toList())

    fun getCell(width: Int): Cell {
        return cells[width]
    }
}
