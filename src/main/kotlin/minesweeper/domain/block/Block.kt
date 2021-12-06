package minesweeper.domain.block

interface Block {
    fun getPosition(): Position

    fun getMineNearCount(): Int?

    fun hasVisited(): Boolean
}
