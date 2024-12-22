package minesweeper.view

import minesweeper.dto.FieldResponse

class OutputView {
    fun printStartGameMessage() {
        println("\n지뢰찾기 게임 시작")
    }

    fun printField(fieldResponse: FieldResponse) {
        println(fieldResponse.toFormattedStringField() + "\n")
    }

    fun printGameLoseMessage() {
        println("Lose Game.")
    }
}
