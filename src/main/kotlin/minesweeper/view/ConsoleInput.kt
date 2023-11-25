package minesweeper.view

object ConsoleInput {
    fun inputBoardHeight(): Int {
        println("높이를 입력하세요.")

        return inputNotEmptyString().toInt()
    }

    fun inputBoardWidth(): Int {
        println("너비를 입력하세요.")

        return inputNotEmptyString().toInt()
    }

    private fun inputNotEmptyString(): String {
        var input: String

        do {
            input = readln()
            if (input.isBlank()) println("공백을 입력할 수 없습니다. 다시 입력해주세요!")
        } while (input.isBlank())

        return input
    }
}
