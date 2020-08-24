package model

enum class MineType {
    NONE, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, MINE;

    companion object {
        fun findByNextType(mineType: MineType): MineType = values()[mineType.ordinal + 1]
    }
}
