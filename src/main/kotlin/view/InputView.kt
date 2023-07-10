package view

class InputView {
    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return inputReadLine().toInt()
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return inputReadLine().toInt()
    }

    fun inputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return inputReadLine().toInt()
    }

    private fun inputReadLine() = readLine()!!
}
