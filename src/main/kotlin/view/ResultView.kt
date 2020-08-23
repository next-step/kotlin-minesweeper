package view

import model.Gamer
import model.Winner

object ResultView {

    fun printStartGame() {
        println("지뢰 찾기 게임 시작")
    }

    fun printError(exception: Throwable?) {
        println(exception?.message)
    }

    fun printBoard(gamer: Gamer) {
        gamer.getCurrentGameBoard()
            .map { mineType -> mineType.map { it.ascii.toChar() } }
            .map { println(it.joinToString()) }
    }

    fun printResult(gamer: Gamer) {
        if (Winner.isLose(gamer)) println("Lose Game.") else println("Win Game.")
    }
}
