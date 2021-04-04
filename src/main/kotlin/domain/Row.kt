package domain

class Row(cells: List<Cell>) {
    private val cells = cells.toList()

    val width: Int
        get() = cells.size

    init {
        require(cells.isNotEmpty()) { "빈 cells 로는 Row 를 생성 할 수 없습니다!" }
    }

    constructor(vararg cells: Cell) : this(cells.toList())
}
