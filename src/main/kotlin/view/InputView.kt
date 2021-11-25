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

    fun askPosition(): Pair<Int, Int> {
        print("\nopen: ")
        val delimiter = ","
        val positions = readInput()
            .split(delimiter)
            .map { it.toInt() }
        require(positions.size == 2) { "좌표는 $delimiter 로 구별되는 2개의 숫자를 입력해야 합니다." }
        return positions[0] to positions[1]
    }

    private fun readNumber(): Int = readInput().toInt()

    private fun readInput(): String {
        val input = readLine()?.filterNot { it.isWhitespace() }
        require(!input.isNullOrBlank()) { "빈 값을 입력할 수는 없습니다." }
        return input
    }
}
