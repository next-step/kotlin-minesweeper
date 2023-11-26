package domain

import domain.enums.CellType
import domain.vo.BoardSettings
import domain.vo.Cell
import domain.vo.Position
import kotlin.random.Random

class GameBoard private constructor(val board: List<List<Cell>>) {

    companion object {
        fun createGameBoard(boardSettings: BoardSettings): GameBoard {
            val board = MutableList(boardSettings.height) { row ->
                MutableList(boardSettings.width) { col ->
                    Cell(Position(col, row))
                }
            }
            installMines(boardSettings, board)

            return GameBoard(board.map { it.toList() })
        }

        private fun installMines(boardSettings: BoardSettings, board: MutableList<MutableList<Cell>>) {
            var minesPlaced = 0
            while (minesPlaced < boardSettings.mineCount) {
                val row = Random.nextInt(boardSettings.height)
                val col = Random.nextInt(boardSettings.width)

                if (board[row][col].cellType != CellType.MINE) {
                    board[row][col] = Cell(Position(col, row), CellType.MINE)
                    minesPlaced++
                }
            }
        }
    }
}
