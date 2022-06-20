package minesweeper.domain

class GameBoard(val cells: Cells) {

    fun click(position: Position): BoardState {
        cells.click(position)
        return cells.state()
    }

    companion object {
        fun of(gameBoardSize: GameBoardSize, mineCount: Int): GameBoard {
            val cellPositions = Positions(gameBoardSize.createPositions())
            val minePositions = cellPositions.createRandomMinePosition(mineCount)
            val cells = Cells.of(cellPositions, minePositions)
            return GameBoard(cells)
        }
    }
}
