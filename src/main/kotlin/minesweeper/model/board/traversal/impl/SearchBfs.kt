package minesweeper.model.board.traversal.impl

import minesweeper.model.board.BoardLimit
import minesweeper.model.board.Mines
import minesweeper.model.board.traversal.SearchEngine
import minesweeper.model.board.traversal.Visited
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Delta
import java.util.LinkedList
import java.util.Queue

class SearchBfs(
    private val limit: BoardLimit,
    private val mines: Mines,
) : SearchEngine {
    override fun traversal(coordinate: Coordinate): Set<Coordinate> {
        val visited = Visited()
        val result: MutableSet<Coordinate> = mutableSetOf()
        val queue: Queue<Coordinate> = LinkedList()
        queue.add(coordinate)

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            result.add(current)
            visited.markVisited(current)
            for (delta: Delta in Delta.deltas) {
                if (current.movePossible(delta, limit)) {
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
        return mines.isGround(next)
    }

    private fun whenZero(next: Coordinate, visited: Visited): Boolean {
        return mines.isGround(next) && mines.isAdjacentMineCountZero(next) && visited.isNotVisited(next)
    }
}
