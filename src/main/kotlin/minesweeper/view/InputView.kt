package minesweeper.view

class InputView {

    fun inputMapHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun inputMapWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun inputMapMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }
}
