package game.minesweeper.domain.map

class MineMap(private val config: MapConfig, var fragments: List<Fragment>) {

    fun setMines(coordinates: List<Coordinate>) {
        fragments = fragments
            .map { if (it.included(coordinates)) it.convertToMine() else it }

        markBorderMine(coordinates)
    }

    fun width() = config.width

    private fun markBorderMine(coordinates: List<Coordinate>) {
        val borders: List<Coordinate> = coordinates.flatMap {
            it.findBorder(config.height, config.width)
        }

        fragments.forEach { it.increaseBorderMine(it.countBorderMine(borders)) }
    }

    companion object {
        private const val START_ROW_NUM = 1
        private const val START_COLUMN_NUM = 1
        fun create(config: MapConfig): MineMap = MineMap(
            config = config,
            fragments = (START_ROW_NUM..config.height).flatMap { x ->
                (START_COLUMN_NUM..config.width).map { y ->
                    Fragment.of(x, y)
                }
            }
        )
    }
}
