package minesweeper

import minesweeper.domain.BoardFactory
import minesweeper.domain.Operation
import minesweeper.domain.Operation.Result.END
import minesweeper.domain.Operation.Result.EXPLOSION
import minesweeper.domain.Operation.Result.OPENED
import minesweeper.domain.Operation.Result.OUT_OF_MATRIX
import minesweeper.domain.Position
import minesweeper.view.BoardView
import minesweeper.view.UserInput

fun main() {
    val height = UserInput.Int("높이를 입력하세요.").answer()
    val width = UserInput.Int("너비를 입력하세요.").answer()
    val bombCount = UserInput.Int("지뢰는 몇 개인가요?").answer()

    val board = BoardFactory(width, height).board(bombCount)
    println("지뢰찾기 게임 시작")
    do {
        BoardView(board).show()
        val operation = board.operation()
        val (x, y) = UserInput.IntArray("\nopen: ").answer()
        operation.open(Position(x, y))
        printResult(operation.result())
    } while (!operation.result().end())

    board.allOpen()
    BoardView(board).show()
}

private fun printResult(result: Operation.Result) {
    when (result) {
        OPENED -> println("이미 열려있습니다.")
        EXPLOSION -> println("Lose Game.")
        END -> println("You Win.")
        OUT_OF_MATRIX -> println("입력범위를 벗어났습니다.")
        else -> println("열었습니다.")
    }
}
