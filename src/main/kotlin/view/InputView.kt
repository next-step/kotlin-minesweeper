package view

class InputView {
    fun getWidth(): Int {
        return println("너비를 입력하세요.").run { readln().trim().toInt() }
    }

    fun getHeight(): Int {
        return println("높이를 입력하세요.").run { readln().trim().toInt() }
    }

    fun getMineCount(): Int {
        return println("지뢰 갯수를 입력하세요.").run { readln().trim().toInt() }
    }
}
