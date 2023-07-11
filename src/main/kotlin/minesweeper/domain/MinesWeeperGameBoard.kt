package minesweeper.domain

import minesweeper.dto.GameBoardRequest

class MinesWeeperGameBoard(gameBoardRequest: GameBoardRequest) {
    private val board: List<List<GameBoardSquare>>
    private val mineLocationGenerator = MineLocationGenerator(RandomCoordinateGenerator())
    private val minesNumber = gameBoardRequest.minesNumber

    init {
        val mutableBoard = gameBoardRequest.createGameBoard()
        generateMines(minesNumber, mutableBoard)
        board = initMineCounts(mutableBoard)
    }

    private fun initMineCounts(mutableBoard: MutableList<MutableList<GameBoardSquare>>): List<List<GameBoardSquare>> {
        val updatedBoard = mutableBoard.mapIndexed { rowIndex, row ->
            row.mapIndexed { columnIndex, square ->
                val nowLocation = Pair(rowIndex, columnIndex)
                countNumOfMineIfNotMine(square, mutableBoard, nowLocation)
            }.toList()
        }.toList()
        return updatedBoard
    }

    private fun countNumOfMineIfNotMine(
        square: GameBoardSquare,
        mutableBoard: MutableList<MutableList<GameBoardSquare>>,
        nowLocation: Pair<Int, Int>
    ): GameBoardSquare {
        if (!square.isMine()) {
            val numOfNearByMine = MineCounter.countNearByMines(mutableBoard, nowLocation)
            return GameBoardSquare.NumberSquare(numOfNearByMine)
        }
        return square
    }

    private fun generateMines(mineNumber: Int, mutableBoard: MutableList<MutableList<GameBoardSquare>>) {
        repeat(mineNumber) {
            val (x, y) = mineLocationGenerator.generateMineLocation(mutableBoard)
            mutableBoard[y][x] = GameBoardSquare.MineSquare()
        }
    }

    fun getBoard(): List<List<GameBoardSquare>> {
        return board.map { it.toList() }
    }
}
