package view

import domain.Point

object View {

    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun inputMineCount(): Int {
        println("지뢰 개수를 입력하세요.")
        return readln().toInt()
    }

    fun inputOpenSpot(): Point {
        print("Open: ")
        return Point(
            readln()
                .split(",")
                .map { it.trim().toInt() }
        )
    }

    fun outputStartGame() {
        println("지뢰찾기 게임을 시작합니다.")
    }

    fun outputWinGame() {
        println("Win Game!")
    }

    fun outputLoseGame() {
        println("Lose Game.")
    }
}
