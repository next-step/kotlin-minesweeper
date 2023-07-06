package minesweeper.view

object InputView {

    fun inputBoardInfo(): Triple<Int, Int, Int> {
        return Triple(
            inputHeight(), inputWidth(), inputMineCount()
        )
    }
    private fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readLine()?.toInt() ?: throw IllegalArgumentException("높이는 숫자여야 합니다.")
    }

    private fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readLine()?.toInt() ?: throw IllegalArgumentException("너비 숫자여야 합니다.")
    }

    private fun inputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readLine()?.toInt() ?: throw IllegalArgumentException("지뢰 개수는 숫자여야 합니다.")
    }
}
