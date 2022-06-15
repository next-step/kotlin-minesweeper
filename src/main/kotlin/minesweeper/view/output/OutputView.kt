package minesweeper.view.output

import minesweeper.model.map.MineMap

interface OutputView {
    fun printInitialMessage() {}
    fun printMap(mineMap: MineMap)
}
