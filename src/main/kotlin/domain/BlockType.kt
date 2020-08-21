package domain

enum class BlockType(val defaultValue: Int) {
    WALL(-2), MINE(-1), GENERAL(0)
}
