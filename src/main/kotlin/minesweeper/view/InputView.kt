package minesweeper.view

class InputView {
    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toIntOrNull() ?: throw IllegalArgumentException("높이는 숫자만 입력 가능합니다.")
    }

    fun inputWidth(): Int {
        println()
        println("너비를 입력하세요.")
        return readln().toIntOrNull() ?: throw IllegalArgumentException("너비는 숫자만 입력 가능합니다.")
    }

    fun inputMineCount(): Int {
        println()
        println("지뢰는 몇 개인가요?")
        return readln().toIntOrNull() ?: throw IllegalArgumentException("지뢰개수는 숫자만 입력 가능합니다.")
    }
}
