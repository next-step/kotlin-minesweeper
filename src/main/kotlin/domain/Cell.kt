package domain

sealed interface Cell {
    object Mine : Cell

    enum class Land(val nearMineCount: Int) : Cell {
        ZERO(0),
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8);

        companion object {
            const val MAXIMUM_NEAR_MINE_COUNT = 8
            private const val MESSAGE_INVALID_MINE_COUNT = "근처에 있는 지뢰의 개수는 0부터 8까지만 존재할 수 있습니다."

            fun from(nearMineCount: Int): Land {
                val land = values().find {
                    it.nearMineCount == nearMineCount
                }
                return requireNotNull(land) {
                    MESSAGE_INVALID_MINE_COUNT
                }
            }
        }
    }
}

fun Cell.increaseMineCount(): Cell {
    return when (this) {
        is Cell.Mine -> Cell.Mine
        is Cell.Land -> Cell.Land.from((this.nearMineCount + 1).coerceAtMost(Cell.Land.MAXIMUM_NEAR_MINE_COUNT))
    }
}
