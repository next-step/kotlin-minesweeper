package domain

import domain.strategy.CreatePointStrategy

class GameBoard private constructor(val board: List<CellList>) {

    companion object {
        fun createBoard(boardSettings: BoardSettings, createPointStrategy: CreatePointStrategy): GameBoard {
            val emptyBoard = createEmptyBoard(boardSettings)
            val board = installMines(boardSettings, emptyBoard, createPointStrategy)

            return GameBoard(board)
        }

        private fun createEmptyBoard(boardSettings: BoardSettings): MutableList<CellList> {
            return (0 until boardSettings.row).map { row ->
                CellList.createEmptyRow(row, boardSettings.col)
            }.toMutableList()
        }

        private fun installMines(boardSettings: BoardSettings, board: MutableList<CellList>, createPointStrategy: CreatePointStrategy): List<CellList> {
            createPointStrategy.createMinePoints(boardSettings).forEach { point ->
                val (row, col) = point / boardSettings.col to point % boardSettings.col
                board[row].cells[col].installMine()
            }
            return board
        }
    }
}
