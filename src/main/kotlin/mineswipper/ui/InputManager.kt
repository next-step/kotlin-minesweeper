package mineswipper.ui

class InputManager {
    fun inputHeight(): Int {
        println(INPUT_NOT_HEIGHT_MESSAGE)
        return inputValueToInt()
    }

    fun inputWidth(): Int {
        println(INPUT_NOT_WIDTH_MESSAGE)
        return inputValueToInt()
    }

    fun inputMine(): Int {
        println(INPUT_NOT_MINE_MESSAGE)
        return inputValueToInt()
    }

    private fun inputValueToInt() = inputUserValue().toInt()

    private fun inputUserValue(): String {
        val input = readln()
        require(input.isNotBlank()) { INPUT_NOT_NULL_MESSAGE }
        return input.trim()
    }

    companion object {
        private const val INPUT_NOT_NULL_MESSAGE = "값을 입력해주세요."
        private const val INPUT_NOT_HEIGHT_MESSAGE = "높이를 입력하세요."
        private const val INPUT_NOT_WIDTH_MESSAGE = "너비를 입력하세요."
        private const val INPUT_NOT_MINE_MESSAGE = "지뢰는 몇 개인가요?"
    }
}
