package presentation

object InputView {
    fun getHeight(): Int {
        println("높이를 입력하세요.")
        return readInt()
    }

    fun getWidth(): Int {
        println("너비를 입력하세요.")
        return readInt()
    }

    fun getMineCount(): Int {
        println("지뢰는 몇개인가요?")
        return readInt()
    }

    private tailrec fun readInt(): Int {
        val enteredInt = readln().toIntOrNull()
        if (enteredInt != null) return enteredInt
        println("다시 입력해 주세요.")
        return readInt()
    }
}
