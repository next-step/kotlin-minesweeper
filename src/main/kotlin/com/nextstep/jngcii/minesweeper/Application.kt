package com.nextstep.jngcii.minesweeper

import com.nextstep.jngcii.minesweeper.domain.MineMapFactory
import com.nextstep.jngcii.minesweeper.domain.RandomPickStrategy
import com.nextstep.jngcii.minesweeper.view.InputView
import com.nextstep.jngcii.minesweeper.view.ResultView

fun main() {
    val meta = InputView.getMineMapMeta()
    val mineCount = InputView.getMineCount()

    val mineMapFactory = MineMapFactory(RandomPickStrategy)
    val mineMap = mineMapFactory.create(meta, mineCount)

    ResultView.printMap(mineMap)
}
