package minesweeper.model.board.traversal

import minesweeper.model.point.Coordinate

interface SearchEngine {
    fun traversal(coordinate: Coordinate): Set<Coordinate>
}
