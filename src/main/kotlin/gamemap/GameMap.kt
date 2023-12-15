package gamemap

import java.util.LinkedList
import java.util.Queue

data class GameMap(
    private val gameMap: List<List<Cell>>
) {
    init {
        require(gameMap.isNotEmpty()) { "invalid game map height" }
        require(gameMap.first().isNotEmpty()) { "invalid game map width" }
        require(gameMap.flatten().any { cell -> cell.isMine }) { "game map should have at least 1 mine cell" }
    }
    fun cellAt(r: Int, c: Int) = gameMap[r][c]

    fun open(r: Int, c: Int): OpenResult {
        val target = cellAt(r, c)

        if (target.isOpen()) return OpenResult.Success

        if (target.isMine) {
            target.open()
            return OpenResult.Fail
        }

        openAllAdjacent(r, c)

        if (remainingCellCount == 0) return OpenResult.Complete

        return OpenResult.Success
    }

    private fun openAllAdjacent(r: Int, c: Int) {
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        val setOfVisited = mutableSetOf<Pair<Int, Int>>()

        queue.add(Pair(r, c))

        while (queue.isNotEmpty()) {
            val pair = queue.poll()

            if (pair in setOfVisited) {
                continue
            }

            val cell = gameMap[pair.first][pair.second]

            cell.open()
            setOfVisited.add(pair)
            if (cell.adjacentMineCount != 0) {
                continue
            }
            queue.addAll(
                adjacentPositions(pair.first, pair.second)
                    .filter { it !in setOfVisited && gameMap[it.first][it.second].isClose() && gameMap[it.first][it.second].isMine.not() }
            )
        }
    }

    private fun adjacentPositions(r: Int, c: Int): List<Pair<Int, Int>> {
        val positions = mutableListOf<Pair<Int, Int>>()
        if (r + 1 < height) {
            positions.add(Pair(r + 1, c))
        }
        if (r - 1 >= 0) {
            positions.add(Pair(r - 1, c))
        }
        if (c + 1 < width) {
            positions.add(Pair(r, c + 1))
        }
        if (c - 1 >= 0) {
            positions.add(Pair(r, c - 1))
        }
        if (r + 1 < height && c + 1 < width) {
            positions.add(Pair(r + 1, c + 1))
        }
        if (r + 1 < height && c - 1 >= 0) {
            positions.add(Pair(r + 1, c - 1))
        }
        if (r - 1 >= 0 && c + 1 < width) {
            positions.add(Pair(r - 1, c + 1))
        }
        if (r - 1 >= 0 && c - 1 >= 0) {
            positions.add(Pair(r - 1, c - 1))
        }

        return positions
    }

    val mineCount = gameMap.flatten().count { cell -> cell.isMine }

    val width = gameMap.first().size

    val height = gameMap.size

    private val remainingCellCount get() = gameMap.flatten().count { cell -> cell.isMine.not() && cell.isClose() }
}

enum class OpenResult {
    Success, Fail, Complete
}
