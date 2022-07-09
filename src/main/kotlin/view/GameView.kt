package view

import domain.Board
import domain.Coordinate
import domain.Empty
import domain.Game
import domain.GameStatus
import domain.Row
import domain.vo.Point

class GameView(val io: IO) {

    fun play(game: Game) {
        io.writeLn()
        io.writeLn("지뢰찾기 게임 시작")
        while (true) {
            when (openCell(game)) {
                GameStatus.WIN -> {
                    io.writeLn("Win Game.")
                    break
                }
                GameStatus.LOST -> {
                    io.writeLn("Lose Game.")
                    break
                }
                GameStatus.CONTINUE -> game.printBoard()
            }
        }
    }

    private fun Game.printBoard() {
        board.rows.forEach {
            io.writeLn(it.toBoardString(board))
        }
        io.writeLn()
    }

    private fun Row.toBoardString(board: Board): String =
        cells.joinToString(CELL_SEPARATOR) {
            if (it is Empty && it.opened) "${board.mineCount(it)}"
            else CELL
        }

    private tailrec fun openCell(game: Game): GameStatus =
        runCatching {
            io.write("open: ")
            val (x, y) = io.read().split(INPUT_SEPARATOR).map { it.toInt() }

            game.open(Coordinate(Point(x), Point(y)))
        }.getOrElse {
            io.writeLn("잘못된 입력입니다.")
            return openCell(game)
        }

    companion object {
        private const val CELL = "C"
        private const val CELL_SEPARATOR = " "
        private const val INPUT_SEPARATOR = ","
    }
}
