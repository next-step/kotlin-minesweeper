package minesweeper.view

class InputView {

    fun inputHeight(): Int {
        println(INPUT_HEIGHT_GUIDE)
        return InputNumberString(readln()).toInt()
    }

    fun inputWidth(): Int {
        println(INPUT_WIDTH_GUIDE)
        return InputNumberString(readln()).toInt()
    }

    fun inputMinesCount(): Int {
        println(INPUT_MINE_COUNT)
        return InputNumberString(readln()).toInt()
    }

    companion object {
        private const val INPUT_HEIGHT_GUIDE = "높이를 입력하세요."
        private const val INPUT_WIDTH_GUIDE = "너비를 입력하세요."
        private const val INPUT_MINE_COUNT = "지뢰는 몇 개인가요?"
    }
}
