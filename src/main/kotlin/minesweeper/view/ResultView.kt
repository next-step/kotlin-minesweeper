package minesweeper.view

import minesweeper.domain.Block
import minesweeper.domain.Point

interface ResultView {
    fun renderInitialBoard(state: Map<Point, Block>)
}
