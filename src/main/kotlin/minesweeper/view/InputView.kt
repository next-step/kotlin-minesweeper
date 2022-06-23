package minesweeper.view

import minesweeper.domain.Coordinate

object InputView {

    fun height(): Int {
        println("높이를 입력하세요.")
        return readln().toInt().also { println() }
    }

    fun width(): Int {
        println("너비를 입력하세요.")
        return readln().toInt().also { println() }
    }

    fun mineCount(): Int {
        println("지뢰는 몇 개 인가요?")
        return readln().toInt().also { println() }
    }

    fun open(): Coordinate {
        print("open: ")
        val split = readln().split(", ")
        return Coordinate(split[0].toInt(), split[1].toInt())
    }
}
