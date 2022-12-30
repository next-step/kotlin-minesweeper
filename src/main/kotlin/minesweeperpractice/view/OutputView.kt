package minesweeperpractice.view

import minesweeperpractice.controller.dto.GameMapDisplayDto

class OutputView {

    fun gameStart(dto: GameMapDisplayDto) {
        println("지뢰찾기 게임 시작")
        val slicedPatterns = dto.blocks.chunked(dto.width)
        slicedPatterns.forEach { slicedPattern ->
            println(slicedPattern.joinToString(" "))
        }
    }
}
