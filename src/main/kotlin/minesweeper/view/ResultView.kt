package minesweeper.view

import minesweeper.domain.block.Block
import minesweeper.domain.exception.MineSweeperException
import minesweeper.domain.Point

interface ResultView {
    fun renderInitialBoard(state: Map<Point, Block>)

    fun printKnownException(exception: MineSweeperException)
    fun printUnknownException(exception: Exception)
}
