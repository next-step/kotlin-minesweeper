package minesweeper.domain

data class MineMap(private val map: List<List<Cell>>) {

    constructor(width: Int, height: Int, mineCount: Int = 0) : this(mutableListOf()) {
        require(width > 0 && height > 0) { "Invalid Mine Map Size" }
        require(mineCount >= 0 && width * height >= mineCount) { "Invalid Mine Count" }

        if (map is MutableList) {
            Array<Cell>(width * height) { NumberCell() }
                .apply { fill(MineCell, 0, mineCount) }
                .apply { shuffle() }
                .toList()
                .chunked(height)
                .forEach { item -> map.add(item) }
        }
    }

    fun open(position: Pair<Int, Int>): Boolean {
        val cell = requireNotNull(map.getOrNull(position.first)?.getOrNull(position.second)) { "Invalid Position" }
        return cell.open()
    }

    fun map(): List<List<Cell>> {
        return map
    }
}

fun mineMap(block: MineMapBuilder.() -> Unit): MineMap = MineMapBuilder().apply(block).build()

class MineMapBuilder {
    private val rows = mutableListOf<List<Cell>>()

    fun rows(vararg cells: Cell) {
        rows.add(cells.toList())
    }

    fun build(): MineMap {
        return MineMap(rows.toList())
    }
}
