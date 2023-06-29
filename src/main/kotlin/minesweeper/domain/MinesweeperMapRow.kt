package minesweeper.domain

data class MinesweeperMapRow(private val mapRow: List<MapElement>) : Iterable<MapElement> by mapRow {

    companion object {
        private const val INVALID_WIDTH_ERROR_MESSAGE = "너비가 0이하일 수 없습니다"
        fun of(rowNumber: Int, width: Int, mineLocations: MineLocations): MinesweeperMapRow {
            require(width > 0) { INVALID_WIDTH_ERROR_MESSAGE }
            val mapRow = List(width) { colNumber -> mineLocations.getMapElement(colNumber, rowNumber) }
            return MinesweeperMapRow(mapRow)
        }
    }
}
