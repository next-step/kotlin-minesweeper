package view

import domain.Column
import domain.Game
import dto.BoardDto

object ResultView {
    fun printBoard(game: Game, boardDto: BoardDto) {
        println("지뢰찾기 게임 시작")

        boardDto.cells.forEachIndexed { index, it ->
            print("$it ")

            if (isSpace(index, game.column)) println()
        }
    }

    private fun isSpace(index: Int, column: Column): Boolean {
        return (index + 1) % column.value == 0
    }
}
