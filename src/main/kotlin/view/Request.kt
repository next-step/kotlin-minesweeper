package view

object Request {
    fun requestInputHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun requestInputWidth(): Int {
        println("너비 입력하세요.")
        return readln().toInt()
    }

    fun requestInputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }
}
