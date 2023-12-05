package minesweeper.model.board.traversal

import minesweeper.model.board.Board
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
                    if (whenZero(next, visited)) {
                        queue.add(next)
                    }
                    if (whenNumber(next)) {
                        result.add(next)
                    }
                }
            }
        }
        return result
    }

    private fun whenNumber(next: Coordinate): Boolean {
        return board.isGround(next)
    }

    private fun whenZero(next: Coordinate, visited: Visited): Boolean {
        return board.isGround(next) && board.isAdjacentMineCountZero(next) && visited.isNotVisited(next)
    }
}
