package game.minesweeper.domain.map

data class Row(var fragments: List<Fragment>) {

    fun setMines(coordinates: List<Coordinate>) {
        fragments = fragments
            .map { if (it.included(coordinates)) it.convertToMine() else it }
    }

    companion object {
        private const val START_COLUMN_NUM = 1
        fun from(rowNum: Int, width: Int): Row = Row((START_COLUMN_NUM..width).map { Fragment.of(rowNum, it) })
    }
}
