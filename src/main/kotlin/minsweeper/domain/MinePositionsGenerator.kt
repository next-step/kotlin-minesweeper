package minsweeper.domain

interface MinePositionsGenerator {

    fun generate(size: BoardSize, mineCount: Int): List<Int>

}
