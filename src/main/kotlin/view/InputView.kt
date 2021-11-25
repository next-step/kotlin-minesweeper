package view

object InputView {
    fun askHeight(): Int {
        println("\n높이를 입력하세요.")
        return readNumber()
    }

    fun askWidth(): Int {
        println("\n너비를 입력하세요.")
        return readNumber()
    }

    fun askMineNumber(): Int {
        println("\n지뢰는 몇 개인가요?")
        return readNumber()
    }

    private fun readNumber(): Int = readInput().toInt()

    private fun readInput(): String {
        val input = readLine()?.filterNot { it.isWhitespace() }
        require(!input.isNullOrBlank()) { "빈 값을 입력할 수는 없습니다." }
        return input
    }
}
