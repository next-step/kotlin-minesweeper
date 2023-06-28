package view

object InputView {

    val length: Int
        get() = println("높이를 입력하세요.").run {
            return inputInt { "높이는 필수입니다." }
        }

    val width: Int
        get() = println("너비를 입력하세요.").run {
            inputInt { "너비는 필수입니다." }
        }

    val mineCount: Int
        get() = println("지뢰는 몇 개인가요?").run {
            inputInt { "지뢰 개수는 필수입니다." }
        }

    private fun inputInt(lazyMessage: () -> String): Int {
        return readlnOrNull()
            ?.toInt()
            .also { println() }
            ?: throw IllegalArgumentException(lazyMessage())
    }
}
