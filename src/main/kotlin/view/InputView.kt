package view

object InputView {
    fun inputHeight(): Int {
        println("높이를 입력하세요")
        return readln().toInt()
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요")
        return readln().toInt()
    }

    fun inputMineCount(): Int {
        println("지뢰의 갯수는 몇개인가요?")
        return readln().toInt()
    }
}
