package minesweeper.model

import minesweeper.model.map.MineMap

fun interface MineMapBuilder {
    fun createNewMap(): MineMap
}
