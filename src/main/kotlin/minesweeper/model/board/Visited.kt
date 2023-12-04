package minesweeper.model.board

import minesweeper.model.point.Coordinate

class Visited {
    private val visitedHistories: MutableSet<Coordinate> = mutableSetOf()

    fun isVisited(coordinate: Coordinate): Boolean {
        return visitedHistories.contains(coordinate)
    }

    fun markVisited(coordinate: Coordinate) {
        visitedHistories.add(coordinate)
    }
}
