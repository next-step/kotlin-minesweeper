package view

import domain.BoardInfo
import domain.Column
import dto.BoardDto

object ResultView {
    fun printStart() {
        println("지뢰찾기 게임 시작")
    }
    fun printBoard(boardInfo: BoardInfo, boardDto: BoardDto) {
        boardDto.cells.forEachIndexed { index, it ->
            print("$it ")

            if (isSpace(index, boardInfo.column)) println()
        }
    }

    fun printLose() {
        println("Lose Game.")
    }

    fun printWin(boardInfo: BoardInfo, boardDto: BoardDto) {
        println("Win Game!")
        printBoard(boardInfo, boardDto)
    }

    private fun isSpace(index: Int, column: Column): Boolean {
        return (index + 1) % column.value == 0
    }
}
