package domain

class MinesWeeper(
    val boards: Array<Array<String>>,
    val mines: Mines
) {
    init {
        mines.values.forEach {
            boards[it.y][it.x] = "*"
        }
    }

    companion object {
        fun of(height: Int, width: Int, count: Int): MinesWeeper {
            val mines = MineGenerator.create(height, width, count)
            val array: Array<Array<String>> = Array(height) { Array(width) { "C" } }
            return MinesWeeper(array, mines)
        }
    }
}
