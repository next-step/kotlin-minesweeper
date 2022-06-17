package minesweeper.domain

class GameBoard(val cells: Cells) {

    companion object {
        fun of(gameBoardSize: GameBoardSize, mineCount: Int): GameBoard {
            val cellPositions = Positions(gameBoardSize.createPositions())
            val minePositions = cellPositions.createRandomMinePosition(mineCount)
            val cells = Cells.of(cellPositions.positions, minePositions)
            return GameBoard(cells)
        }
    }
}
