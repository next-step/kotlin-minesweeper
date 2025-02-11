package view

object OutputView {
    fun printGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printLoseGame() {
        println("Lose Game")
    }

    fun printBoard(board: String) {
        println(board)
    }
}
