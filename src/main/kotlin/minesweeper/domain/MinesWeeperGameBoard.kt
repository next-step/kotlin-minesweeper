package minesweeper.domain

import minesweeper.dto.GameBoardRequest

class MinesWeeperGameBoard(
    height: Int,
    width: Int,
    minesNumber: Int
) {
    private val board: MutableList<MutableList<GameBoardSquare>> =
        MutableList(height) { MutableList(width) { GameBoardSquare(SquareValueType.EMPTY) } }
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
        board[y][x].insertMine()
    }

    fun getBoard(): List<List<GameBoardSquare>> {
        return board.map { it.toList() }
    }
}
