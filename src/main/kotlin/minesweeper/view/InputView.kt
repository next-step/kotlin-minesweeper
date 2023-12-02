package minesweeper.view

import minesweeper.model.point.Coordinate

object InputView {

    fun mapHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun mapWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun countOfMines(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }

    fun openCoordinate(parser: CoordinateParser): Coordinate {
        print("open : ")
        return parser.parse(readln())
    }
}
