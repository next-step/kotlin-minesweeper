package minesweeper.domain

import minesweeper.dto.GameBoardRequest

class MinesWeeperGameBoard(gameBoardRequest: GameBoardRequest) {
    private val board: List<List<GameBoardSquare>>
    private val mineLocationGenerator = MineLocationGenerator(RandomCoordinateGenerator())
    private val minesNumber = gameBoardRequest.minesNumber

    init {
        val mutableBoard = gameBoardRequest.createGameBoard()
        generateMines(minesNumber, mutableBoard)
        board = mutableBoard.map { it.toList() }
    }

    private fun generateMines(mineNumber: Int, mutableBoard: MutableList<MutableList<GameBoardSquare>>) {
        repeat(mineNumber) {
            val (x, y) = mineLocationGenerator.generateMineLocation(mutableBoard)
            mutableBoard[y][x] = GameBoardSquare(SquareValueType.MINE)
        }
    }

    fun getBoard(): List<List<GameBoardSquare>> {
        return board.map { it.toList() }
    }
}
