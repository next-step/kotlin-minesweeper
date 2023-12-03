package domain

import domain.strategy.CreatePointStrategy

class GameBoard (val board: List<CellList> = emptyList()) {

    fun from(boardSettings: BoardSettings, createPointStrategy: CreatePointStrategy): GameBoard {
        val board = createEmptyBoard(boardSettings)
        installMines(boardSettings, board, createPointStrategy)
        createNeighborMinesCount(boardSettings, board)

        return GameBoard(board)
    }

    private fun createEmptyBoard(boardSettings: BoardSettings): MutableList<CellList> {
        return (0 until boardSettings.row).map { row ->
            CellList().createEmptyRow(row, boardSettings.col)
        }.toMutableList()
    }

    private fun installMines(boardSettings: BoardSettings, board: MutableList<CellList>, createPointStrategy: CreatePointStrategy) {
        createPointStrategy.createMinePoints(boardSettings).forEach {
            board[it.row].cells[it.col].installMine()
        }
    }

    private fun createNeighborMinesCount(boardSettings: BoardSettings, board: List<CellList>) {
        board.map { it.findCellListByNeighborMineCount(boardSettings, board) }
    }

}
