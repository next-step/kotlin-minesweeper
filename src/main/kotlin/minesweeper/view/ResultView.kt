package minesweeper.view

import minesweeper.domain.Point
import minesweeper.domain.block.Block
import minesweeper.domain.exception.MineSweeperException

interface ResultView {
    fun renderInitialBoard(state: Map<Point, Block>)
    fun printKnownException(exception: MineSweeperException)
    fun printUnknownException(exception: Exception)
}
