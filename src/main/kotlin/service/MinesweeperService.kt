package service

import domain.Mine
import domain.Minesweeper

class MinesweeperService {

    fun initGame(height: Int, width: Int, mineCount: Int): Minesweeper {
        val init = Minesweeper(height = height, width = width, mines = List(mineCount) { Mine() })
        return init.distributeMine()
    }

}