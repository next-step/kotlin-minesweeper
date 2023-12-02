package minesweeper.domain

import java.util.*

class Board(
    private val height: Height,
    private val width: Width,
    private val mineCount: MineCount,
    private val minePlacementStrategy: MinePlacementStrategy = RandomMinePlacementStrategy()
) {
    val rows: SortedSet<Row>
        get() = generateMap()

    init {
        require(mineCount.value < height.value * width.value) { "지뢰 숫자는 보드 크기보다 작아야 합니다." }
    }

    private fun generateMap(): SortedSet<Row> {
        val sortedSet: SortedSet<Row> = sortedSetOf<Row>()
        for (y in 0 until height.value) {
            sortedSet.add(RowFactory.createRows(width, y))
        }
        placeMines(sortedSet)

        return sortedSet
    }

    private fun placeMines(rows: SortedSet<Row>) {
        val allCells = rows.flatMap { it.cells }.toMutableList()
        val mineIndices = minePlacementStrategy.selectIndices(allCells, mineCount.value)
        mineIndices.forEach { index ->
            allCells[index].state = State.MINE
        }
    }
}
