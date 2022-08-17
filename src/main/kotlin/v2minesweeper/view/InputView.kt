package v2minesweeper.view

object InputView {
    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun inputMineNumber(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }

    fun inputOpenPosition(): Pair<Int, Int> {
        print("open: ")
        return readln().split(",")
            .map { it.trim().toInt() }
            .let { it[0] to it[1] }
    }
}
