package com.nextstep.jngcii.minesweeper

import com.nextstep.jngcii.minesweeper.domain.MineManager
import com.nextstep.jngcii.minesweeper.domain.MineMapFactory
import com.nextstep.jngcii.minesweeper.domain.RandomPickStrategy
import com.nextstep.jngcii.minesweeper.view.InputView
import com.nextstep.jngcii.minesweeper.view.ResultView
import kotlin.random.Random

fun main() {
    val rowCount = InputView.getRowCount()
    val columnCount = InputView.getColumnCount()
    val mineCount = InputView.getMineCount()

    val mineManager = MineManager(
        booleanStrategy = { Random.nextBoolean() },
        pickStrategy = RandomPickStrategy
    )
    val mineMapFactory = MineMapFactory(mineManager)

    val mineMap = mineMapFactory.create(rowCount, columnCount, mineCount)

    ResultView.printMap(mineMap)
}
