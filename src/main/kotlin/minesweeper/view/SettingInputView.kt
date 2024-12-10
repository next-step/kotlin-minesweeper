package minesweeper.view

object SettingInputView {
    fun parseHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun parseWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun parseMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }
}
