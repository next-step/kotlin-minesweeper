package minesweeper.domain

object MineSweeperMap {
    private const val INIT_POSITION = "C"
    private const val MINE_POSITION = "*"

    fun getMap(mines: Mines, rows: Int, cols: Int): Array<Array<String>> {
        val map = Array(rows) { Array(cols) { INIT_POSITION } }
        mines.list.forEach {
            map[it.row][it.col] = MINE_POSITION
        }
        return map
    }
}
