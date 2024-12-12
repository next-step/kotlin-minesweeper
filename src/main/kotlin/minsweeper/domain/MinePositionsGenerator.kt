package minsweeper.domain

interface MinePositionsGenerator {

    fun generate(param: BoardParam): List<Int>

}
