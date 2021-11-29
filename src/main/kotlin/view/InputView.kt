package view

object InputView {

    private const val DEFAULT_HEIGHT = "0"
    private const val DEFAULT_WIDTH = "0"
    private const val DEFAULT_MINE_COUNT = "0"

    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return (readLine() ?: DEFAULT_HEIGHT).toInt()
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return (readLine() ?: DEFAULT_WIDTH).toInt()
    }

    fun inputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return (readLine() ?: DEFAULT_MINE_COUNT).toInt()
    }
}
