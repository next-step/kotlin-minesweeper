package domain

class MinesWeeper(
    val boards: Array<Array<String>>,
    val mines: Mines
) {
    init {
        mines.values.forEach {
            boards[it.y][it.x] = MINE_STRING
        }
    }

    companion object {

        private const val MINE_STRING = "*"

        private const val BASIC_STRING = "C"

        fun of(height: Int, width: Int, count: Int): MinesWeeper {
            val mines = MineGenerator.create(height, width, count)
            val array: Array<Array<String>> = Array(height) { Array(width) { BASIC_STRING } }
            return MinesWeeper(array, mines)
        }
    }
}
