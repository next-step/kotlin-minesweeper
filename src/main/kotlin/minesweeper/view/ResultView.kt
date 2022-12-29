package minesweeper.view

import minesweeper.domain.Block
import minesweeper.domain.MineSweeperException
import minesweeper.domain.Point

interface ResultView {
    fun renderInitialBoard(state: Map<Point, Block>)

    fun printKnownException(exception: MineSweeperException)
    fun printUnknownException(exception: Exception)
}
