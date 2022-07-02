package minesweeper.dto

import minesweeper.model.GameStatus
import minesweeper.model.MineBoard

class GameResultDto private constructor(
    val boardRows: List<BoardRowDto>,
    val gameStatus: GameStatus
) {

    val isOngoing
        get() = gameStatus.isOngoing()

    val win
        get() = gameStatus.win()

    val lost
        get() = gameStatus.lost()

    companion object {

        fun from(board: MineBoard): GameResultDto {
            val boardRows = board.board.map { BoardRowDto.of(it, board) }

            val boardRowContainingMine = boardRows.firstOrNull { it.containsMine() }
            if (boardRowContainingMine != null) {
                return GameResultDto(boardRows, GameStatus.LOST)
            }

            val closeMarkCount = boardRows.sumOf { it.countOfCloseMark }
            if (board.mineCount == closeMarkCount) {
                return GameResultDto(boardRows, GameStatus.WIN)
            }

            return GameResultDto(boardRows, GameStatus.ONGOING)
        }
    }
}
