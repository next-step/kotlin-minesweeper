package minesweeper.domain

import java.util.*

class Board(
    private val boardInformation: BoardInformation,
    private val minePlacementStrategy: MinePlacementStrategy = RandomMinePlacementStrategy()
) {
    val mineMap: List<Row>
        get() = generateMap()

    init {
        require(boardInformation.isMineCountSmallerThanBoardSize()) { "지뢰 숫자는 보드 크기보다 작아야 합니다." }
    }

    private fun generateMap(): List<Row> {
        val sortedSet: SortedSet<Row> = sortedSetOf<Row>()
        for (y in 0 until boardInformation.height.value) {
            sortedSet.add(RowFactory.createRows(boardInformation.width, y))
        }
        val allCells = sortedSet.toMutableList()
        return minePlacementStrategy.placeMine(allCells, boardInformation.mineCount.value).toList()
    }
}
