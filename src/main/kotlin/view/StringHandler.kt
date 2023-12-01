package view

import domain.Cell

const val MINE_SYMBOL = "X"

fun inputToInt(input: String): Int {
    require(input.isNotBlank()) { "빈 값이 입력되었습니다." }

    val num = input.toIntOrNull()
    require(num != null) { "숫자만 입력되어야 합니다." }

    return num
}

fun Cell.toStr(): String {
    if (isMine) return MINE_SYMBOL
    return this.hint?.toString() ?: throw IllegalStateException("null 값에 대한 String 변환이 요청되었습니다")
}
