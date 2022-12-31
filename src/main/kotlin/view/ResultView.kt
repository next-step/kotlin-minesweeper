package view

import domain.Column
import domain.ResultStatus
import dto.BoardDto

object ResultView {
    fun printStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printResult(result: ResultStatus, boardDto: BoardDto) {
        if (result == ResultStatus.WIN) printWin(boardDto)
        else printLose()
    }
    fun printBoard(boardDto: BoardDto) {
        boardDto.cells.forEachIndexed { index, it ->
            print("$it ")

            if (isSpace(index, boardDto.column)) println()
        }
    }

    fun printLose() {
        println("Lose Game.")
    }

    fun printWin(boardDto: BoardDto) {
        println("Win Game!")
        printBoard(boardDto)
    }

    private fun isSpace(index: Int, column: Column): Boolean {
        return (index + 1) % column.value == 0
    }
}
