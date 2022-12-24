package minesweeper.interfaces.ui

object OutputConsole {
    fun printComponents(components: List<ComponentDto>, width: Int) {
        println("지뢰찾기 게임 시작")
        components.forEach {
            val mine = if (it.isMine) "*" else "C"
            print("$mine ")
            if (isLastString(y = it.y, width = width)) {
                println()
            }
        }
        println()
    }

    private fun isLastString(y: Int, width: Int) = y == width - 1
}

data class ComponentDto(
    val x: Int,
    val y: Int,
    val isMine: Boolean
)
