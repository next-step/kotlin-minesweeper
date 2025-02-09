package tdd.minesweeper.ui

import tdd.minesweeper.domain.Location

interface InputView {
    fun inputHeight(): Int

    fun inputWidth(): Int

    fun inputCountOfMines(): Int

    fun inputLocation(): Location
}
