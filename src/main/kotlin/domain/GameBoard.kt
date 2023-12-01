package domain

import domain.strategy.CreatePointStrategy

class GameBoard private constructor(val board: List<CellList>) {

    companion object {
        fun createBoard(boardSettings: BoardSettings, createPointStrategy: CreatePointStrategy): GameBoard {
            val emptyBoard = createEmptyBoard(boardSettings)
            val board = installMines(boardSettings, emptyBoard, createPointStrategy)
            val gameBoard = createNeighborMinesCount(boardSettings, board)

            return GameBoard(gameBoard)
        }

        private fun createEmptyBoard(boardSettings: BoardSettings): MutableList<CellList> {
            return (0 until boardSettings.row).map { row ->
                CellList.createEmptyRow(row, boardSettings.col)
            }.toMutableList()
        }

        private fun installMines(boardSettings: BoardSettings, board: MutableList<CellList>, createPointStrategy: CreatePointStrategy): List<CellList> {
            createPointStrategy.createMinePoints(boardSettings).forEach {
                val (row, col) = it / boardSettings.col to it % boardSettings.col
                board[row].cells[col].installMine()
            }
            return board
        }

        private fun createNeighborMinesCount(boardSettings: BoardSettings, board: List<CellList>): List<CellList> {
            return board.map { it.createCellList(boardSettings, board) }
        }
    }
}
