package view

import model.Game

object Output {
    fun drawMap(game: Game) {
        println("지뢰찾기 게임 시작")
        println(game.toString())
    }

    fun lose() {
        println("you lose")
    }

    fun win() {
        println("you win")
    }
}
