package game.minesweeper.domain.map

class MineMap(private val config: MapConfig, val rows: List<Row>) {

    fun setMines(coordinates: List<Coordinate>) {
        coordinates.groupBy { it.x }
            .forEach { rows[it.key - 1].setMines(it.value) }

        val borders: List<Coordinate> = coordinates.flatMap {
            it.findBorder(config.height, config.width)
        }

        rows.flatMap { it.fragments }
            .forEach { it.increaseBorderMine(it.count(borders)) }
    }

    companion object {
        private const val START_ROW_NUM = 1
        fun create(config: MapConfig): MineMap = MineMap(
            config,
            (START_ROW_NUM..config.height)
                .map { Row.from(it, config.width) }
        )
    }
}
