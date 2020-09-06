package view

object Output {
    fun drawMap(game: Game) {
        println("지뢰찾기 게임 시작")
        println(game.toString())
    }

    fun win() {
        println("you win")
    }
}
