package controller

import domain.Field
import domain.RandomPositionSelector
import view.inputHeight
import view.inputMineNum
import view.inputWidth
import view.printBoard
import view.printStartMessage

fun main() {
    val height = inputHeight()
    val width = inputWidth()
    val mineNum = inputMineNum()

    // 가로,세로길이 입력 & 필드 생성
    val field = Field(width = width, height = height)

    // 지뢰 배치
    val selector = RandomPositionSelector(width = width, height = height)
    repeat(mineNum) {
        field.setMine(selector)
    }

    // 힌트 설정
    field.setHints()

    printStartMessage()
    printBoard(field.cells)
}
