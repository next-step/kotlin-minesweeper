package minesweeper.domain

interface MineGenerator {
    fun generate(
        height: Height,
        width: Width,
        mineCount: MineCount,
    ): List<Mine>
}
