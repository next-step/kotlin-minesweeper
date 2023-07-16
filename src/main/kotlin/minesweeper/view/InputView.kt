package minesweeper.view

object InputView {
    fun receiveHeight(): Int {
        println("높이를 입력하세요.")

        return receiveNotNullNumber()
    }

    fun receiveWidth(): Int {
        println()
        println("너비를 입력하세요.")

        return receiveNotNullNumber()
    }

    fun receiveMineCount(): Int {
        println()
        println("지뢰는 몇 개인가요?")

        return receiveNotNullNumber()
    }

    private fun receiveString(): String {
        var input: String? = null

        do {
            input = readlnOrNull()
        } while (input.isNullOrBlank())

        return input
    }

    private fun receiveNotNullNumber(): Int {
        var input: Int? = receiveString().toIntOrNull()

        while (input == null) {
            input = receiveString().toIntOrNull()
        }

        return input
    }
}
