package model.board

class Row(cells: List<Cell>) {
    var cells = cells.toList()
        private set

    val width: Int
        get() = cells.size

    init {
        require(cells.isNotEmpty()) { "빈 cells 로는 Row 를 생성 할 수 없습니다!" }
    }

    constructor(vararg cells: Cell) : this(cells.toList())

    operator fun get(widthIndex: Int): Cell = cells[widthIndex]

    private fun isMine(widthIndex: Int): Boolean = cells[widthIndex].isMine

    fun uncover(widthIndex: Int, mineCount: Int) {
        cells = cells.toMutableList().apply {
            this[widthIndex] = this[widthIndex].asUncovered(mineCount)
        }.toList()
    }

    fun countMine(widthRange: IntRange): Int =
        widthRange.sumBy { widthIndex -> if (isMine(widthIndex)) 1 else 0 }
}
