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

enum class MapElement {
    ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, MINE;

    companion object {
        private val INVALID_VALUE_ERROR_MESSAGE = "지뢰 숫자는 ${ZERO.ordinal} ~ ${EIGHT.ordinal}사이여야 합니다"

        fun of(value: Int): MapElement {
            require(value in ZERO.ordinal..EIGHT.ordinal) { INVALID_VALUE_ERROR_MESSAGE }
            return values().first { it.ordinal == value }
        }
    }
}
