package minesweeper.domain

class MineMap(width: Int, height: Int, mineCount: Int = 0) {

    private val map: List<List<Cell>>

    init {
        require(width > 0 && height > 0) { "Invalid Mine Map Size" }
        require(mineCount >= 0 && width * height >= mineCount) { "Invalid Mine Count" }
        map = Array<Cell>(width * height) { NumberCell() }
            .apply { fill(MineCell, 0, mineCount) }
            .apply { shuffle() }
            .toList()
            .chunked(height)
    }

    fun map(): List<List<Cell>> {
        return map
    }
}

fun mineMap(block: MineMapBuilder.() -> Unit): List<List<Cell>> = MineMapBuilder().apply(block).build()

@JvmInline
value class Cells(private val cells: List<Cell> = emptyList()) {
    operator fun get(index: Int?): Cell {
        return index?.let { MineCell } ?: NumberCell()
    }
}

class MineMapBuilder {
    private val rows = mutableListOf<List<Cell>>()

    fun rows(vararg cells: Cell) {
        rows.add(cells.toList())
    }

    fun build(): List<List<Cell>> {
        return rows.toList()
    }
}
