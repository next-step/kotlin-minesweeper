package com.nextstep.jngcii.minesweeper

import com.nextstep.jngcii.minesweeper.domain.MineMapFactory
import com.nextstep.jngcii.minesweeper.domain.RandomPickStrategy
import com.nextstep.jngcii.minesweeper.view.InputView
import com.nextstep.jngcii.minesweeper.view.ResultView

fun main() {
    val rowCount = InputView.getRowCount()
    val columnCount = InputView.getColumnCount()
    val mineCount = InputView.getMineCount()

    val mineMapFactory = MineMapFactory(RandomPickStrategy)
    val mineMap = mineMapFactory.create(rowCount, columnCount, mineCount)

    ResultView.printMap(mineMap)
}
