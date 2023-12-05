package domain

import domain.strategy.CreatePointStrategy
import domain.strategyImpl.RandomPointFactory

class GameBoard private constructor(
    board: List<CellList> = emptyList(),
    val gameResult: GameResult = GameResult(),
    val boardSettings: BoardSettings,
    val createPointStrategy: CreatePointStrategy
) {
    var board: List<CellList> = board
        private set

    init {
        createEmptyBoard(boardSettings)
        installMines(boardSettings)
        createNeighborMinesCount(boardSettings)
    }

    private fun createEmptyBoard(boardSettings: BoardSettings) {
        board =  (0 until boardSettings.row).map { row ->
            CellList().createEmptyRow(row, boardSettings.col)
        }.toList()
    }

    private fun installMines(boardSettings: BoardSettings) {
        createPointStrategy.createMinePoints(boardSettings).forEach {
            board[it.row].cells[it.col].installMine()
        }
    }

    private fun createNeighborMinesCount(boardSettings: BoardSettings) {
        board.map { it.findCellListByNeighborMineCount(boardSettings, board) }
    }

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
        val validNeighborCells = point.getValidNeighborCells(board)
        validNeighborCells.filter { it.isNotMine() && !it.cellInfo.isOpened }
            .forEach { it.openCell() }
    }

    private fun Point.getValidNeighborCells(board: List<CellList>): List<Cell> {
        return getNeighborPoints()
            .filter { it.isValid(board.size, board[0].cells.size) }
            .map { board[it.row].cells[it.col] }
    }

    companion object {
        fun of(
            boardSettings: BoardSettings,
            createPointStrategy: CreatePointStrategy = RandomPointFactory()
        ): GameBoard = GameBoard(
            boardSettings = boardSettings,
            createPointStrategy = createPointStrategy
        )
    }
}
