package minesweeper.domain

import minesweeper.domain.enums.CellStatus

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
        val cell = requireNotNull(cell(position)) { "Invalid Position" }
        return cell.open()
            .also { safeAroundPositions(position).onEach { open(it) } }
    }

    fun map(): List<List<Cell>> {
        return map
    }

    fun safeAroundPositions(position: Pair<Int, Int>): List<Pair<Int, Int>> {
        return cell(position)
            ?.takeIf { (it is NumberCell) && it.mineCountAround == 0 }
            ?.let {
                listOf(
                    Pair(position.first - 1, position.second - 1),
                    Pair(position.first - 1, position.second),
                    Pair(position.first - 1, position.second + 1),
                    Pair(position.first, position.second - 1),
                    Pair(position.first, position.second + 1),
                    Pair(position.first + 1, position.second - 1),
                    Pair(position.first + 1, position.second),
                    Pair(position.first + 1, position.second + 1)
                )
            }
            ?.filter { (numberCell(it)?.let { cell -> cell.status == CellStatus.CLOSE }) ?: false }
            ?: emptyList()
    }

    private fun numberCell(position: Pair<Int, Int>): NumberCell? {
        return cell(position) as? NumberCell
    }

    private fun cell(position: Pair<Int, Int>): Cell? {
        return map.getOrNull(position.first)?.getOrNull(position.second)
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
