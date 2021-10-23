package view

object InputView {
    fun readHeight(): Int {
        return readWhileEmpty("높이를 입력하세요.").toInt()
    }

    fun readWidth(): Int {
        return readWhileEmpty("너비를 입력하세요.").toInt()
    }

    fun readMineCount(): Int {
        return readWhileEmpty("지뢰는 몇 개인가요?").toInt()
    }

    private fun readWhileEmpty(message: String): String {
        println(message)

        var raw = readLine()
        while (raw.isNullOrEmpty()) {
            raw = readLine()
        }
        return raw
    }

    fun readPosition(): Pair<Int, Int> {
        return readWhileEmpty("open : ").split(",")
            .map { it.trim().toInt() - 1 }
            .zipWithNext()
            .first()
    }
}