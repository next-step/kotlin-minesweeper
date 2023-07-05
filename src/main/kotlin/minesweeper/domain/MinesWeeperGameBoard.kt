package minesweeper.domain

import minesweeper.dto.GameBoardRequest

class MinesWeeperGameBoard(
    height: Int,
    width: Int,
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

    fun getBoard(): List<List<Char>> {
        return board.map { it.toList() }
    }
}
