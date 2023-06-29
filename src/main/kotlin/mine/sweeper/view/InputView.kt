package mine.sweeper.view

object InputView {
    fun getHeight(): Int {
        println("높이를 입력하세요.")
        val input = readln()
        require(input.toIntOrNull() != null) { "높이를 숫자를 입력해주세요!" }
        return input.toInt()
    }

    fun getWidth(): Int {
        println("너비를 입력하세요.")
        val input = readln()
        require(input.toIntOrNull() != null) { "너비를 숫자를 입력해주세요!" }
        return input.toInt()
    }

    fun getMines(): Int {
        println("지뢰는 몇 개인가요?")
        val input = readln()
        require(input.toIntOrNull() != null) { "지뢰의 개수를 숫자를 입력해주세요!" }
        return input.toInt()
    }
}
