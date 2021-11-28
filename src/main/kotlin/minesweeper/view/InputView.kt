package minesweeper.view

object InputView {

    fun getHeight(): Int {
        println("높이를 입력하세요")
        return getInt()
    }

    fun getWidth(): Int {
        println("너비를 입력하세요")
        return getInt()
    }

    fun getMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return getInt()
    }

    private fun getInt() = getInput().toInt()

    private fun getInput() = readLine()!!
}
