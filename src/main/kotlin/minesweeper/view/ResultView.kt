package minesweeper.view

import minesweeper.model.MineBoardResult

interface ResultView {
    fun printMineBoard(boardResult: MineBoardResult)
}
