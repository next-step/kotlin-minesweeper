package minsweeper.domain

interface MineCoordinatesGenerator {

    fun generate(size: BoardSize, mineCount: Int): List<Coordinate>

}
