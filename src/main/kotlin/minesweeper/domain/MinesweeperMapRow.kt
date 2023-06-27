package minesweeper.domain

data class MinesweeperMapRow(private val mapRow: List<MapElement>) : Iterable<MapElement> by mapRow {

    companion object {
        private const val MINE_INDICES_SIZE_EXCEED_WIDTH_ERROR_MESSAGE = "한줄의 지뢰 개수가 맵의 너비보다 많을 수 없습니다"
        private const val INVALID_WIDTH_ERROR_MESSAGE = "너비가 0이하일 수 없습니다"

        fun of(row: MineLocationRow, width: Int): MinesweeperMapRow {
            require(width > 0) { INVALID_WIDTH_ERROR_MESSAGE }
            require(row.count() <= width) { MINE_INDICES_SIZE_EXCEED_WIDTH_ERROR_MESSAGE }

            val mapRow = List(width) { isMineToMapElement(row.contains(it)) }
            return MinesweeperMapRow(mapRow)
        }

        fun isMineToMapElement(isMine: Boolean): MapElement {
            if (isMine) {
                return MapElement.MINE
            }
            return MapElement.NORMAL
        }
    }
}

enum class MapElement {
    NORMAL, MINE
}
