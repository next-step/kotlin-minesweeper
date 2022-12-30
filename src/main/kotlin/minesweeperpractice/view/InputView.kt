package minesweeperpractice.view

import minesweeperpractice.controller.dto.GameMapRequestDto

class InputView {

    fun createMap(): GameMapRequestDto {
        // 높이 입력
        println("높이를 입력하세요.")
        val height = readln().toInt()

        // 너비 입력
        println("너비를 입력하세요.")
        val width = readln().toInt()

        // 지뢰 개수 입력
        println("지뢰는 몇 개인가요?")
        val mineCount = readln().toInt()

        return GameMapRequestDto(height, width, mineCount)
    }
}
