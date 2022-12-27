package minesweeper.view

class OutputView {

    fun printGameResult() {
        println(OUTPUT_GAME_START_GUIDE)
    }

    companion object {
        private const val OUTPUT_GAME_START_GUIDE = "지뢰찾기 게임 시작"
    }
}
