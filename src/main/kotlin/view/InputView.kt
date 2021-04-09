package view

object InputView {
    private fun readWhileEmpty(message: String): String {
        println(message)

        var input: String? = null
        while (input.isNullOrBlank()) {
            input = readLine()
        }

        return input
    }

    fun readHeight(): Int {
        return readWhileEmpty("높이를 입력하세요.").toInt()
    }

    fun readWidth(): Int {
        return readWhileEmpty("너비를 입력하세요.").toInt()
    }

    fun readMineCount(): Int {
        return readWhileEmpty("지뢰는 몇 개인가요?").toInt()
    }
}
