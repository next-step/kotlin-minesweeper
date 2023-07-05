package minesweeper.view

object InputView {
    fun getCols(): Int {
        println("높이를 입력하세요.")
        return getNumber()
    }

    fun getRows(): Int {
        println("너비를 입력하세요.")
        return getNumber()
    }

    fun getMine(): Int {
        println("지뢰는 몇 개인가요?")
        return getNumber()
    }

    private fun getNumber(): Int {
        return readLine()?.toInt() ?: throw IllegalArgumentException()
    }
}
