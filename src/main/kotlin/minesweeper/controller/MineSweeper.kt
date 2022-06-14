package minesweeper.controller

import minesweeper.model.MineMapBuilder
import minesweeper.view.output.OutputView

class MineSweeper(
    private val mineMapBuilder: MineMapBuilder,
    private val outputView: OutputView? = null
) {

    fun run() {
        val map = mineMapBuilder.createNewMap()
        outputView?.printInitialMessage()
        outputView?.printMap(map)
    }
}
