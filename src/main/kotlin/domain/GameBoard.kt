package domain

import domain.strategy.CreatePointStrategy

class GameBoard private constructor(
    val board: List<CellList> = emptyList(),
    val gameResult: GameResult = GameResult()
) {

    fun isContinued(): Boolean = gameResult.isContinued()

    fun openCells(point: Point) {
        openOwnCell(point)
        openNeighborCells(point)
        gameResult.checkGameStatus(point, board)
    }

    private fun openOwnCell(point: Point) {
        board[point.row].cells[point.col].openCell()
    }

    private fun openNeighborCells(point: Point) {
        point.getNeighborPoints()
            .filter { it.isValid(board.size, board[0].cells.size) }
            .map { board[it.row].cells[it.col] }
            .filter { it.isNotMine() && !it.cellInfo.isOpened }
            .forEach { it.openCell() }
    }

    companion object {
        fun createGameBoard(boardSettings: BoardSettings, createPointStrategy: CreatePointStrategy): GameBoard {
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
}
