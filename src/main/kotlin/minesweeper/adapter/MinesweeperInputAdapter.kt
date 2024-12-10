package minesweeper.adapter

import minesweeper.domain.FieldHeight
import minesweeper.domain.FieldWidth
import minesweeper.domain.MineCount

interface MinesweeperInputAdapter {
    fun fetchFieldWidth(): FieldWidth

    fun fetchFieldHeight(): FieldHeight

    fun fetchMineCount(): MineCount
}
