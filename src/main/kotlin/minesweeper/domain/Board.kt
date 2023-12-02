package minesweeper.domain

import java.util.SortedSet

class Board(private val height: Height, private val width: Width) {
    val rows: SortedSet<Row>
        get() = generateMap()

    private fun generateMap(): SortedSet<Row> {
        val sortedSet: SortedSet<Row> = sortedSetOf<Row>()
        for (y in 0 until height.value) {
            sortedSet.add(RowFactory.createRows(width, y))
        }
        return sortedSet
    }
}
