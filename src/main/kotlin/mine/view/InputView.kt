package mine.view

object InputView {
    fun inputHeight(): Int? {
        println("높이를 입력하세요.")
        return readLine()?.toIntOrNull()
    }

    fun inputWidth(): Int? {
        println("너비를 입력하세요.")
        return readLine()?.toIntOrNull()
    }

    fun inputMineCount(): Int? {
        println("지뢰는 몇 개인가요?")
        return readLine()?.toIntOrNull()
    }
}
