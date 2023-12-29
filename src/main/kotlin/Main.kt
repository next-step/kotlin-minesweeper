fun main() {
    val height = inputHeight()
    val width = inputWidth()
    val mineSize = inputMineSize()

    val mineSweeper = MineSweeper(width, height, mineSize, RandomMinePositionsGenerator(width, height))
    printMineSweeperMap(mineSweeper)
}

private fun inputHeight(): Int {
    println("높이를 입력하세요.")
    return readln().toInt()
}

private fun inputWidth(): Int {
    println("너비를 입력하세요.")
    return readln().toInt()
}

private fun inputMineSize(): Int {
    println("지뢰는 몇 개인가요?")
    return readln().toInt()
}

private fun printMineSweeperMap(mineSweeper: MineSweeper) {
    println("지뢰찾기 게임 시작")
    (0 until mineSweeper.height).forEach{ y ->
        printRowOfMineSweeperMap(mineSweeper, y)
    }
}

private fun printRowOfMineSweeperMap(mineSweeper: MineSweeper, y: Int) {
    val row = (0 until mineSweeper.width)
        .map { x -> Position(y, x) }
        .map { mineSweeper.cells().getValue(it) }
        .joinToString(separator = " ") { statusToText(it) }
    println(row)
}

private fun statusToText(status: Status): String {
    return when(status) {
        Status.EMPTY -> "C"
        Status.MINE -> "*"
    }
}
