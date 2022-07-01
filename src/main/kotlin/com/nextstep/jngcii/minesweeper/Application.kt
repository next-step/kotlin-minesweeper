package com.nextstep.jngcii.minesweeper

import com.nextstep.jngcii.minesweeper.domain.MineBoardFactory
import com.nextstep.jngcii.minesweeper.domain.ShuffleOrderStrategy
import com.nextstep.jngcii.minesweeper.view.InputView
import com.nextstep.jngcii.minesweeper.view.ResultView

fun main() {
    val meta = InputView.getMineMapMeta()
    val mineCount = InputView.getMineCount()

    val mineBoardFactory = MineBoardFactory(ShuffleOrderStrategy)
    val mineMap = mineBoardFactory.create(meta, mineCount)

    ResultView.printMap(mineMap)
}
