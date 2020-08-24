package view

import model.Gamer
import model.MineType
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
            .map { mineType -> mineType.map { convertMineTypeToSymbol(it) } }
            .map { println(it.joinToString()) }
    }

    fun printResult(gamer: Gamer) {
        if (Winner.isLose(gamer)) println("Lose Game.") else println("Win Game.")
    }

    private fun convertMineTypeToSymbol(mineType: MineType): Char =
        when (mineType) {
            MineType.NONE -> 'C'
            MineType.ZERO -> '0'
            MineType.ONE -> '1'
            MineType.TWO -> '2'
            MineType.THREE -> '3'
            MineType.FOUR -> '4'
            MineType.FIVE -> '5'
            MineType.SIX -> '6'
            MineType.SEVEN -> '7'
            MineType.EIGHT -> '8'
            MineType.NINE -> '9'
            MineType.MINE -> '*'
        }
}
