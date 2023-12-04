package minesweeper.model.board

import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Delta
import java.util.LinkedList
import java.util.Queue

class Bfs(
    private val board: Board,
) {
    fun traversal(coordinate: Coordinate): Set<Coordinate> {
        val visited: Array<Array<Boolean>> = board.limit.toVisited()
        val result: MutableSet<Coordinate> = mutableSetOf()
        val queue: Queue<Coordinate> = LinkedList()
        queue.add(coordinate)

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            result.add(current)
            visited[current.verticalValue()][current.horizontalValue()] = true
            for (d: Delta in Delta.deltas) {
                if (current.movePossible(d, board.limit)) {
                    val next = current.moveTo(d)
                    if ((board.attribute(next) == Attribute.NONE) && (visited[next.verticalValue()][next.horizontalValue()] == false)) {
                        queue.add(next)
                    }
                }
            }
        }
        return result
    }
}
