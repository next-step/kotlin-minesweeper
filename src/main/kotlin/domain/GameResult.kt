package domain

import domain.enums.GameStatus

class GameResult(val gameStatus: GameStatus = GameStatus.PLAYING) {

    fun getGameStatus(point: Point, board: List<CellList>): GameStatus {
        if (isGameLose(point, board)) { return GameStatus.LOSE }
        if (isGameWin(board)) { return GameStatus.WIN }

        return GameStatus.PLAYING
    }

    private fun isGameWin(board: List<CellList>): Boolean {
        return board.all { it.cells.all { cell -> cell.cellInfo.isOpened || cell.cellInfo.isMine() } }
    }

    private fun isGameLose(point: Point, board: List<CellList>): Boolean = board[point.row].cells[point.col].cellInfo.isMine()
}
