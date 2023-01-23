package views

object Input {
    fun getHeight(): Int {
        println("높이를 입력하세요.")
        return readLine()?.toIntOrNull() ?: throw IllegalArgumentException("It is not a number")
    }

    fun getWidth(): Int {
        println("너비를 입력하세요.")
        return readLine()?.toIntOrNull() ?: throw IllegalArgumentException("It is not a number")
    }

    fun getMine(): Int {
        println("지뢰는 몇 개인가요?")
        return readLine()?.toIntOrNull() ?: throw IllegalArgumentException("It is not a number")
    }
}
