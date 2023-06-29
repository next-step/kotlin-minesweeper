package tdd.minesweeper.ui

class GameInputProxy(private val target: GameInput) : GameInput {

    override fun requestHeight(): Int =
        target.requestHeight().takeIf(::isPositive) ?: run(::requestHeight)

    override fun requestWidth(): Int =
        target.requestWidth().takeIf(::isPositive) ?: run(::requestWidth)

    override fun requestMineCapacity(): Int =
        target.requestMineCapacity().takeIf(::isPositive) ?: run(::requestMineCapacity)

    override fun requestMarkingPoint(): Pair<Int, Int> =
        target.requestMarkingPoint().takeIf { isPositive(it.first) && isPositive(it.second) }
            ?: run(::requestMarkingPoint)

    private fun isPositive(it: Int) = it > 0
}
