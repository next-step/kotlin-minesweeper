package domain

import kotlin.random.Random

class GameBoard private constructor(val board: Array<Array<Char>>) {

    companion object {
        private const val NOT_MINE = 'C'
        const val MINE = '*'

        fun createGameBoard(boardSettings: BoardSettings): GameBoard {
            val board = Array(boardSettings.height) { Array(boardSettings.width) { NOT_MINE } }
            installMines(boardSettings, board)

            return GameBoard(board)
        }

        private fun installMines(boardSettings: BoardSettings, board: Array<Array<Char>>) {
            var minesPlaced = 0
            while (minesPlaced < boardSettings.mineCount) {
                val row = Random.nextInt(boardSettings.height)
                val col = Random.nextInt(boardSettings.width)

                if (board[row][col] != MINE) {
                    board[row][col] = MINE
                    minesPlaced++
                }
            }
        }
    }
}
