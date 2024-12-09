package minesweeper.entity

interface MineGenerator {
    fun generate(
        allCoordinates: List<Coordinate>,
        mineCount: MineCount,
    ): Set<Coordinate>
}
