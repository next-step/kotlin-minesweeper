package model

enum class MineType {
    NONE, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, MINE;

    companion object {
        fun findByOrdinal(ordinal: Int): MineType = values()[ordinal]
    }
}
