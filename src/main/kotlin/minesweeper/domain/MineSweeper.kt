package minesweeper.domain

class MineSweeper private constructor(
    val board: Array<Array<String>>
) {
    companion object {
        private const val INIT_POSITION = "C"
        private const val MINE_POSITION = "*"

        fun getMap(mines: Mines, rows: Int, cols: Int): MineSweeper {
            val map = Array(rows) { Array(cols) { INIT_POSITION } }
            mines.list.forEach {
                map[it.row][it.col] = MINE_POSITION
            }
            return MineSweeper(map)
        }
    }
}
