package com.nextstep.minesweeper.controller

import com.nextstep.minesweeper.domain.MineDispenseHelper.Companion.defaultCalculatePolicy
import com.nextstep.minesweeper.domain.MineField
import com.nextstep.minesweeper.view.InputView
import com.nextstep.minesweeper.view.OutputView

fun main() {
    OutputView.printInputHeightMessage()
    val height = InputView.inputMessage()

    OutputView.printWidthHeightMessage()
    val width = InputView.inputMessage()

    OutputView.printMineCountMessage()
    val mineCounts = InputView.inputMessage()

    val mineField = createMineField(height, width, mineCounts)

    OutputView.printMineFields(mineField)
}

private fun createMineField(
    height: String,
    width: String,
    mineCounts: String
): MineField {
    val mineField = MineField(height.toInt(), width.toInt(), defaultCalculatePolicy())
    mineField.dispense(mineCounts.toInt())
    return mineField
}

