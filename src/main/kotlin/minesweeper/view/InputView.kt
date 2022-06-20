package minesweeper.view

object InputView {
    fun height(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun width(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun mine(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }

    fun click(): List<Int> {
        print("open : ")
        return readln().split(",").map { it.toInt() }.also {
            require(it.size == 2) { "선택 할 좌표는 x, y 두개만 입력 가능합니다." }
        }
    }
}
