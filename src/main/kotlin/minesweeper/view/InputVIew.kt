package minesweeper.view

class InputVIew {
    fun inputFieldHeight(): String {
        println("높이를 입력하세요.")
        return readln()
    }

    fun inputFieldWidth(): String {
        println("\n너비를 입력하세요.")
        return readln()
    }

    fun inputMineCount(): String {
        println("\n지뢰는 몇 개인가요?")
        return readln()
    }
}
