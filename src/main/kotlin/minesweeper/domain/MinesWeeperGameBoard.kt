package minesweeper.domain

import minesweeper.dto.GameBoardRequest

class MinesWeeperGameBoard(
    private val height: Int,
    private val width: Int,
    private val minesNumber: Int
) {
    private var board: Array<Array<Char>> = Array(height) { Array(width) { 'C' } }
    private val mineLocationGenerator = MineLocationGenerator(height, width, RandomCoordinateGenerator())

    constructor(gameBoardRequest: GameBoardRequest) : this(
        gameBoardRequest.height,
        gameBoardRequest.width,
        gameBoardRequest.minesNumber
    )

    init {
        require(height * width >= minesNumber) { "지뢰의 갯수는 전체 게임판 보다 크면 안됩니다." }
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
        board[x][y] = '*'
    }

    fun getBoard(): Array<Array<Char>> {
        return Array(height) { i -> Array(width) { j -> board[i][j] } }
    }
}
