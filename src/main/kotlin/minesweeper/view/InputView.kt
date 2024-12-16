package minesweeper.view

/**
 * @author 이상준
 */
class InputView {
    fun inputHeight(): Int {
        println("높이를 입력해주세요.")
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException()
    }

    fun inputWidth(): Int {
        println("너비를 입력해주세요.")
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException()
    }

    fun inputMineCount(): Int {
        println("지뢰의 개수를 입력해주세요.")
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException()
    }
}
