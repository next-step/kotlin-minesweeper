package minesweeper.domain

class MineSweeper(val mineMap: MineMap, val mines: Mines) {
    init {
        mines.validateMines(mineMap)
    }
}
