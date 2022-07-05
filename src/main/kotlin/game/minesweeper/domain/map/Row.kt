package game.minesweeper.domain.map

data class Row(private val _fragments: List<Fragment>) {

    fun fragments() = _fragments

    fun setMines(coordinates: List<Coordinate>) {
        _fragments
            .filter { it.included(coordinates) }
            .forEach { it.setMine() }
    }

    companion object {
        private const val START_COLUMN_NUM = 1
        fun from(rowNum: Int, width: Int) = Row((START_COLUMN_NUM..width).map { Fragment.of(rowNum, it) })
    }
}
