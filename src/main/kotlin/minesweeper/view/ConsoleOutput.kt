package minesweeper.view

class ConsoleOutput {
    fun printMap(width: Int, haveMines: List<Boolean>) {
        println("지뢰찾기 게임 시작")

        haveMines.chunked(width)
            .forEach { println(it.joinToString(separator = " ") { hasMine -> MineViewModel.findModel(hasMine) }) }
    }
}

enum class MineViewModel(private val hasMine: Boolean, private val model: String) {
    DEFAULT(false, "C"),
    MINE(true, "*");

    companion object {
        fun findModel(hasMine: Boolean) = values().first { it.hasMine == hasMine }.model
    }
}
