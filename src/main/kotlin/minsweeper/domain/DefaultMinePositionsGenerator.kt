package minsweeper.domain

class DefaultMinePositionsGenerator : MinePositionsGenerator {

    override fun generate(param: BoardParam): List<Int> =
        (0 until param.height * param.width).shuffled().take(param.mineCount)

}
