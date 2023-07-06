package minesweeper.domain

class MineSweeper private constructor(
    val board: Array<Array<String>>
) {
    companion object {
        private const val INIT_POSITION = "C"
        private const val MINE_POSITION = "*"

        fun getMap(mines: Mines, size: Size): MineSweeper {
            val map = Array(size.row) { Array(size.col) { INIT_POSITION } }
            mines.list.forEach {
                map[it.row][it.col] = MINE_POSITION
            }
            return MineSweeper(map)
        }
    }
}
