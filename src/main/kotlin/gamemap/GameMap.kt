package gamemap

import java.util.LinkedList
import java.util.Queue

class GameMap(
    private val gameMap: List<List<Cell>>
) {
    init {
        require(gameMap.isNotEmpty()) { "invalid game map height" }
        require(gameMap.first().isNotEmpty()) { "invalid game map width" }
        require(gameMap.flatten().any { cell -> cell.isMine }) { "game map should have at least 1 mine cell" }
    }

    fun cellAt(row: Int, col: Int) = gameMap[row][col]

    fun openCellAt(row: Int, col: Int): OpenCommandResult {
        val target = cellAt(row, col)

        if (target.isOpen()) return OpenCommandResult.Success

        if (target.isMine) {
            target.open()
            return OpenCommandResult.Fail
        }

        openFloodFill(row, col)

        if (remainingCellCount == 0) return OpenCommandResult.Complete

        return OpenCommandResult.Success
    }

    private data class TargetCellPosition(
        val row: Int,
        val col: Int,
    ) {
        fun adjacentPositionsIn(width: Int, height: Int): List<TargetCellPosition> {
            val positions = mutableListOf<TargetCellPosition>()

            val deltas = listOf(
                Pair(-1, -1), Pair(-1, 0), Pair(-1, 1),
                Pair(0, -1), Pair(0, 1),
                Pair(1, -1), Pair(1, 0), Pair(1, 1),
            )

            deltas.forEach {
                val targetRowIdx = row + it.first
                val targetColIdx = col + it.second
                if ((targetRowIdx in 0 until height) && (targetColIdx in 0 until width)) {
                    positions.add(TargetCellPosition(row = targetRowIdx, col = targetColIdx))
                }
            }

            return positions
        }
    }

    private fun openFloodFill(row: Int, col: Int) {
        val queue: Queue<TargetCellPosition> = LinkedList()
        val setOfVisited = mutableSetOf<TargetCellPosition>()

        queue.add(TargetCellPosition(row = row, col = col))

        while (queue.isNotEmpty()) {
            val targetPosition = queue.poll()

            if (targetPosition in setOfVisited) continue

            val cell = gameMap[targetPosition.row][targetPosition.col]

            cell.open()

            setOfVisited.add(targetPosition)

            if (cell.adjacentMineCount != 0) continue

            queue.addAll(
                targetPosition.adjacentPositionsIn(width, height)
                    .filter {
                        it !in setOfVisited && gameMap[it.row][it.col].hasToBeOpened()
                    }
            )
        }
    }

    val mineCount = gameMap.flatten().count { cell -> cell.isMine }

    val width = gameMap.first().size

    val height = gameMap.size

    private val remainingCellCount
        get() = gameMap.flatten()
            .count { cell -> cell.hasToBeOpened() }

    private fun Cell.hasToBeOpened() = isMine.not() && isClose()
}
