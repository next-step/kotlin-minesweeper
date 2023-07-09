package minesweeper.view

object InputView {
    fun receiveHeight(): Int {
        println("높이를 입력하세요.")

        return receiveInt()
    }

    fun receiveWidth(): Int {
        println("너비를 입력하세요.")

        return receiveInt()
    }

    fun receiveMineCount(): Int {
        println("지뢰는 몇 개인가요?")

        return receiveInt()
    }

    private fun receiveString(): String {
        var input: String? = null

        do {
            input = readlnOrNull()
        } while (input.isNullOrBlank())

        return input
    }

    private fun receiveInt(): Int {
        var int: Int? = receiveString().toIntOrNull()

        while (int == null) {
            int = receiveString().toIntOrNull()
        }

        return int
    }
}