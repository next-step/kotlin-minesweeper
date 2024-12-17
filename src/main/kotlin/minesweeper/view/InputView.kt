package minesweeper.view

object InputView {
    fun readHeight(): Int {
        println("높이를 입력하세요.")
        return readlnOrNull()?.toInt() ?: throw IllegalStateException("높이는 Integer 타입으로 입력하세요")
    }

    fun readWidth(): Int {
        println("너비를 입력하세요.")
        return readlnOrNull()?.toInt() ?: throw IllegalStateException("너비는 Integer 타입으로 입력하세요")
    }

    fun readMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readlnOrNull()?.toInt() ?: throw IllegalStateException("지뢰 개수는 Integer 타입으로 입력하세요")
    }
}
