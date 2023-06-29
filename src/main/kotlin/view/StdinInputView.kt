package view

import java.lang.IllegalArgumentException

class StdinInputView : InputView {
    override fun getHeight(): Int {
        println("높이를 입력하세요.")
        val height = readln()
        return height.toIntOrNull()
            ?: throw IllegalArgumentException("높이는 숫자값만 입력할 수 있습니다")
    }

    override fun getWidth(): Int {
        println("너비를 입력하세요.")
        val width = readln()
        return width.toIntOrNull()
            ?: throw IllegalArgumentException("너비는 숫자값만 입력할 수 있습니다")
    }

    override fun getMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        val mine = readln()
        return mine.toIntOrNull()
            ?: throw IllegalArgumentException("지뢰 갯수는 숫자값만 입력할 수 있습니다")
    }
}
