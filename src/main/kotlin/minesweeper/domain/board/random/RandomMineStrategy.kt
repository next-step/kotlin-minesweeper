package minesweeper.domain.board.random

interface RandomMineStrategy {

    fun strategy(): (numberOfCells: Int, numberOfMines: Int) -> List<Int>
}
