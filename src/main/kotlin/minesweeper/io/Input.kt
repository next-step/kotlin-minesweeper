package minesweeper.io

import minesweeper.domain.Coordinate

class Input {
    fun getWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun getHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun getMineCount(): Int {
        println("지뢰는 몇 개 인가요.")
        return readln().toInt()
    }

    fun getCoordinate(): Coordinate {
        print("open :")
        val int = readln().split(",").map { it.toInt() }
        return Coordinate(int[0], int[1])
    }
}
