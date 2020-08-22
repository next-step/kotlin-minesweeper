package view

import model.Board

object ResultView {

    fun printStartGame() {
        println("지뢰 찾기 게임 시작")
    }

    fun printError(exception: Throwable?) {
        println(exception?.message)
    }
}
