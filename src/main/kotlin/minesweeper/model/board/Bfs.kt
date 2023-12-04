package minesweeper.model.board

import minesweeper.model.point.Coordinate
import minesweeper.model.point.Delta
import java.util.LinkedList
import java.util.Queue

class Bfs(
    private val board: Board,
) {
    fun traversal(coordinate: Coordinate): Set<Coordinate> {
        val visited = Visited()
        val result: MutableSet<Coordinate> = mutableSetOf()
        val queue: Queue<Coordinate> = LinkedList()
        queue.add(coordinate)

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            result.add(current)
            visited.markVisited(current)
            for (delta: Delta in Delta.deltas) {
                if (current.movePossible(delta, board.limit)) {
                    val next = current.moveTo(delta)
                    if (board.isGroundAttribute(next) && visited.isVisited(next) && board.isAdjacentMineCountZero(next)) {
                        queue.add(next)
                    }
                }
            }
        }
        return result
    }
}
