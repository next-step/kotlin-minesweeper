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
        point.getNeighborPoints()
            .filter { it.isValid(board.size, board[0].cells.size) }
            .map { board[it.row].cells[it.col] }
            .filter { it.isNotMine() && !it.cellInfo.isOpened }
            .forEach { it.openCell() }
    }

//    디미터 법칙을 위반하고 있습니다. 이정도는 직접 접근해도 무방하겠지만
//    GameBoard 및 CellList를 별도로 정의한 김에 각각의 책임을 나누고 메서드 네이밍으로 의도를 명확히 드러내면 어떨까요?
//    그리고 체이닝이 길어지면서 메서드의 가독성이 다소 떨어지게 되었는데요.
//    List<Cell>에 대해서도 확장함수 등으로 getNotOpenedAndNotMine, openCells 같은 메서드를 제공해도 가독성이 올라갈 것 같네요.

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
