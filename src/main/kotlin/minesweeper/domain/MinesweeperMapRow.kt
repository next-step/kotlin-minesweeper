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

sealed interface MapElement

object MineMapElement : MapElement

data class NumberMapElement(private val value: Int) : MapElement {
    init {
        require(value in MIN_MINE_COUNT..MAX_MINE_COUNT) { INVALID_VALUE_ERROR_MESSAGE }
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        private const val MIN_MINE_COUNT = 0
        private const val MAX_MINE_COUNT = 8
        private const val INVALID_VALUE_ERROR_MESSAGE = "지뢰 숫자는 $MIN_MINE_COUNT ~ ${MAX_MINE_COUNT}사이여야 합니다"
    }
}
