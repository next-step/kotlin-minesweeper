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
        EIGHT(8)
    }
}
