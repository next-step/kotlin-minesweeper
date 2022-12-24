package minesweeper.service

import minesweeper.model.MineMap
import minesweeper.model.Mines

object MineCellSelector {
    fun selectRandomMines(mineMap: MineMap, mineCount: Int): Mines {
        val shuffledMines = mineMap.flatten()
            .shuffled()
            .take(mineCount)
            .toSet()
        return Mines(shuffledMines)
    }
}
