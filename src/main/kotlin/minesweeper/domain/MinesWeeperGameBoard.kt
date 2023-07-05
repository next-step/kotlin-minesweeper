package minesweeper.domain

import minesweeper.dto.GameBoardRequest

class MinesWeeperGameBoard(
    private val height: Int,
    private val width: Int,
    minesNumber: Int
) {
    private val board: Array<Array<Char>> = Array(height) { Array(width) { 'C' } }
    private val mineLocationGenerator = MineLocationGenerator(RandomCoordinateGenerator())
    private val gameBoardValidator = GameBoardValidator()

    constructor(gameBoardRequest: GameBoardRequest) : this(
        gameBoardRequest.height,
        gameBoardRequest.width,
        gameBoardRequest.minesNumber
    )

    init {
        gameBoardValidator.validateGameRequest(height, width, minesNumber)
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
        board[y][x] = '*'
    }

    fun getBoard(): Array<Array<Char>> {
        return Array(height) { i -> Array(width) { j -> board[i][j] } }
    }
}
