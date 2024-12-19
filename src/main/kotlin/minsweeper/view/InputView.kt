package minsweeper.view

import minsweeper.domain.Coordinate

object InputView {

    fun showAndGetWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun showAndGetHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun showAndGetMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }

    fun showAndGetOpenCoordinate(): String {
        print("open: ")
        return readln()
    }

}
