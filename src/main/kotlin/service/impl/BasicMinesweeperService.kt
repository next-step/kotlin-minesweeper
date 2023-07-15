package service.impl

import domain.Mine
import domain.Minesweeper
import enums.MinesweeperShape
import service.MinesweeperService

class BasicMinesweeperService : MinesweeperService {

    override fun initGame(height: Int, width: Int, mineCount: Int): Minesweeper {
        val init = Minesweeper(
            height = height,
            width = width,
            mines = List(mineCount) { Mine(MinesweeperShape.MINE) },
        )
        return init.distributeMine()
    }
}
