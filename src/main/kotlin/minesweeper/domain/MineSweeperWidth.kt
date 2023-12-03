package minesweeper.domain

class MineSweeperWidth(
    private val widthList: List<String>,
) : List<String> by widthList {

    companion object {
        fun newInstance(widthSize: Int, value: String): MineSweeperWidth {
            return (1..widthSize).map {
                value
            }.toMineSweeperWidth()
        }
    }
}

fun List<String>.toMineSweeperWidth() = MineSweeperWidth(this)
