package com.nextstep.minesweeper

import com.nextstep.minesweeper.domain.Board
import com.nextstep.minesweeper.view.InputView
import com.nextstep.minesweeper.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    val height = inputView.inputHeight()
    val width = inputView.inputWidth()
    val numberOfMines = inputView.inputNumberOfMines()

    val board = Board(height, width, numberOfMines)

    outputView.printGameStart()
}
