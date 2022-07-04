package minesweeper.domain

class Board(
    private val boardSize: BoardSize,
    val cells: List<Cell>
) {

    private val minesCount = cells.count { it == Mine }

    constructor(boardSize: BoardSize, minesCount: Int) : this(
        boardSize = boardSize,
        cells = (1..boardSize.area).map {
            if (it <= minesCount) Mine
            else Opened()
        }.shuffled()
    )

    init {
        require(boardSize.area == cells.size) { "board area[${boardSize.area} is not equals to cell size [${cells.size}]" }
        require(boardSize.area > minesCount) { "Mines count[$minesCount] must be smaller than area[${boardSize.area}] of the board" }
    }

    fun rows(): List<List<Cell>> {
        return cells.chunked(boardSize.width)
    }

    fun open() {
        cells.forEachIndexed { index, it ->
            if (it != Mine) return@forEachIndexed

            nearCells(index).forEach { it.increment() }
        }
    }

    private fun nearCells(index: Int): List<Opened> {
        return nearCellIds(index)
            .filter { it in (0 until boardSize.area) }
            .map { cells[it] }
            .filterIsInstance<Opened>()
    }

    private fun nearCellIds(index: Int): Set<Int> {
        val width = boardSize.width
        return when (index % width) {
            0 -> setOf(index - width, index - width + 1, index + 1, index + width, index + width + 1)
            width - 1 -> setOf(index - width - 1, index - width, index - 1, index + width - 1, index + width)
            else -> setOf(
                index - width - 1,
                index - width,
                index - width + 1,
                index - 1,
                index + 1,
                index + width - 1,
                index + width,
                index + width + 1
            )
        }
    }
}
