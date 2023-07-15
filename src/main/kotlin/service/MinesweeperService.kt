package service

import domain.Minesweeper

interface MinesweeperService {
    fun initGame(height: Int, width: Int, mineCount: Int): Minesweeper
}