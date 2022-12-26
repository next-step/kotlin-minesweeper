package minesweeper.view

object InputView {
    tailrec fun readHeight(): Int {
        println("높이를 입력하세요.")
        val userInput: String = readln()

        if (userInput.toIntOrNull() != null) return userInput.toInt()
        return readHeight()
    }

    tailrec fun readWidth(): Int {
        println("너비를 입력하세요.")
        val userInput: String = readln()

        if (userInput.toIntOrNull() != null) return userInput.toInt()
        return readWidth()
    }

    tailrec fun readMineCount(): Int {
        println("지뢰는 몇개인가요?")
        val userInput: String = readln()

        if (userInput.toIntOrNull() != null) return userInput.toInt()
        return readMineCount()
    }
}
