package minesweeper.domain.board.strategy

interface MineStrategy {

    fun getMineIndices(numberOfCells: Int, numberOfMines: Int): List<Int>
}
