package minesweeper.view

import minesweeper.dto.FieldResponse

class OutputView {
    fun printStartGameMessage() {
        println("\n지뢰찾기 게임 시작")
    }

    fun printField(fieldResponse: FieldResponse) {
        println(fieldResponse.toFormattedStringField() + "\n")
    }

    fun printAlreadyOpenedMessage(fieldResponse: FieldResponse) {
        println("이미 열린 위치입니다.")
        printField(fieldResponse)
    }

    fun printGameLoseMessage() {
        println("Lose Game.")
    }
}
