package view

object InputView {

    fun requestInputByMode(mode: Mode): Int {
        println(mode.message)
        return readLine()?.toInt() ?: 0
    }

    enum class Mode(val message: String) {
        ROW("높이를 입력하세요."),
        COL("너비를 입력하세요."),
        MINE_COUNT("지뢰는 몇 개인가요?")
    }
}
