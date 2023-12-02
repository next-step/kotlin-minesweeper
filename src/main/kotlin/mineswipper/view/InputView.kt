package mineswipper.view

object InputView {
    fun receiveHeight(): Int {
        return getInteger("높이를 입력하세요.")
    }

    fun receiveWidth(): Int {
        return getInteger("너비를 입력하세요.")
    }

    fun receiveMineCount(): Int {
        return getInteger("지뢰는 몇 개인가요?")
    }

    private fun getInteger(message: String): Int {
        println(message)
        return readln().toInt()
    }
}
