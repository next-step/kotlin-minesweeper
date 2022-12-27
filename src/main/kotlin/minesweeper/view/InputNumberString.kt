package minesweeper.view

class InputNumberString(
    private val value: String
) {
    init {
        require(value.isNotBlank()) { "값이 비어있습니다." }
        require(isNumber()) { "숫자가 아닙니다. (입력값:$value)" }
    }

    private fun isNumber(): Boolean {
        return value.toCharArray().all { it in '0'..'9' }
    }

    fun toInt(): Int {
        return value.toInt()
    }
}