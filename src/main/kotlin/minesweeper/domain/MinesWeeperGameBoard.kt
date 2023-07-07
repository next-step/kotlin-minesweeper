package minesweeper.domain

import minesweeper.dto.GameBoardRequest

class MinesWeeperGameBoard(gameBoardRequest: GameBoardRequest) {
    private val board: MutableList<MutableList<GameBoardSquare>> = gameBoardRequest.createGameBoard()
    private val mineLocationGenerator = MineLocationGenerator(RandomCoordinateGenerator())
    private val minesNumber = gameBoardRequest.minesNumber

    init {
        generateMines(minesNumber)
    }

    private fun generateMines(mineNumber: Int) {
        repeat(mineNumber) {
            val generatedMineLocation = mineLocationGenerator.generateMineLocation(getBoard())
            insertMine(generatedMineLocation)
        }
    }

    private fun insertMine(mineLocation: MineLocation) {
        val (x, y) = mineLocation
        board[y][x].insertMine()
    }

    fun getBoard(): List<List<GameBoardSquare>> {
        return board.map { it.toList() }
    }
}
