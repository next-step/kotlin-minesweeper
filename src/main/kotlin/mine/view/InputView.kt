package mine.view

object InputView {
    fun getHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toIntOrNull() ?: throw RuntimeException("숫자를 입력해주세요")
    }

    fun getWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toIntOrNull() ?: throw RuntimeException("숫자를 입력해주세요")
    }

    fun getMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toIntOrNull() ?: throw RuntimeException("숫자를 입력해주세요")
    }
}
