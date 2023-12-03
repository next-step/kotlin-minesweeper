package minesweeper.view

object OutputView {
    private const val GAME_START = "지뢰찾기 게임 시작"

    fun drawField(fieldView: List<String>) {
        println()
        println(GAME_START)
        fieldView.forEach { println(it) }
    }
}
