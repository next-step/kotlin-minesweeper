package domain

class EmptyElement private constructor(override val displayCharacter: String, override val location: Location) : MapElement {
    var mineCnt = 0

    // 주변에 지뢰가 몇 개 있는지 계산하여 mineCnt에 업데이트
    fun countMine(mineMap: MineMap) {
        val directions = listOf(
            Pair(-1, -1), Pair(-1, 0), Pair(-1, 1),
            Pair(0, -1), Pair(0, 1),
            Pair(1, -1), Pair(1, 0), Pair(1, 1)
        )

        for (direction in directions) {
            val row = location.row + direction.first
            val col = location.column + direction.second
            mineCnt += if (mineMap.isMine(Location(row, col))) 1 else 0
        }
    }

    companion object {
        private const val EMPTY_ELEMENT_CHAR = "C"

        fun create(x: Int, y: Int): EmptyElement {
            return EmptyElement(EMPTY_ELEMENT_CHAR, Location(x, y))
        }
    }
}
