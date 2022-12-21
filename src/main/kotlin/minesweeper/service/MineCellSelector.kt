package minesweeper.service

import minesweeper.model.MineMap
import minesweeper.model.Mines

object MineCellSelector {
    fun randomMines(map: MineMap, mineCount: Int): Mines {
        return Mines(map.flatten().shuffled().take(mineCount).toSet())
    }
}
