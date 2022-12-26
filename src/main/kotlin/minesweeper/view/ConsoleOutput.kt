package minesweeper.view

class ConsoleOutput {
    fun printMap(width: Int, height: Int, hasMine: List<Boolean>) {
        println("지뢰찾기 게임 시작")
        (0 until height).forEach { currentHeight ->
            val row = hasMine.subList(currentHeight * width, (currentHeight + 1) * width)
            println(row.map { MineViewModel.find(it) }.joinToString(separator = " "))
        }
    }
}

enum class MineViewModel(private val hasMine: Boolean, private val model: String) {
    DEFAULT(false, "C"),
    MINE(true, "*");

    companion object {
        fun find(hasMine: Boolean) = values().first { it.hasMine == hasMine }.model
    }
}
