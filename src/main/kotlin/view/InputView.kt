package view

class InputView {

    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readlnOrNull()?.toIntOrNull() ?: 0
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readlnOrNull()?.toIntOrNull() ?: 0
    }

    fun inputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readlnOrNull()?.toIntOrNull() ?: 0
    }
}
