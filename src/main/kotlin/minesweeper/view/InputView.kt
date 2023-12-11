package minesweeper.view

import minesweeper.domain.Position

class InputView {
    fun getHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun getWidth(): Int {
        println("\n너비를 입력하세요.")
        return readln().toInt()
    }

    fun getCountOfMine(): Int {
        println("\n지뢰는 몇 개인가요?")
        return readln().toInt()
    }

    fun getPosition(): Position {
        print("open: ")
        return readln().split(", ").map { it.toInt() }.let {
            Position(it[0], it[1])
        }
    }
}
