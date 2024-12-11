package minesweeper.view

import minesweeper.dto.FieldResponse

class OutputView {
    fun printInitialField(fieldResponse: FieldResponse) {
        println("\n지뢰찾기 게임 시작\n" + fieldResponse.toFormattedStringInitialField())
    }
}
