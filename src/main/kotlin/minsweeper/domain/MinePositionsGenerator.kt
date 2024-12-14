package minsweeper.domain

interface MinePositionsGenerator {

    fun generate(area: Int, mineCount: Int): List<Int>

}
