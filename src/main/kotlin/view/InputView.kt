package view

object InputView {
    fun inputHeight(): Int {
        return inputRequireNumber("높이를 입력하세요.")
    }

    fun inputWidth(): Int {
        return inputRequireNumber("너비를 입력하세요.")
    }

    fun inputMineCount(): Int {
        return inputRequireNumber("지뢰는 몇 개인가요?")
    }

    private fun inputRequireNumber(message: String): Int {
        println(message)
        val value = readln()
        return runCatching {
            require(value.isInt()) { "0보다 큰 숫자여야합니다." }
            value.toInt()
        }
            .fold(
                onSuccess = {
                    println()
                    it
                },
                onFailure = { e ->
                    println(e.message)
                    inputRequireNumber(message)
                }
            )
    }

    private fun String.isInt(): Boolean = this.toIntOrNull() != null
}
