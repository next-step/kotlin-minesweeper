package minesweeper.view

object InputView {
    fun inputRowSize(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun inputColumnSize(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun inputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }

    fun inputOpenPosition(): Pair<Int, Int> {
        print("open : ")
        val split = readln().split(",")
        return Pair(split[0].toInt() - 1, split[1].toInt() - 1)
    }
}
