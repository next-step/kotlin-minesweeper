package minesweeper.ui

class MineSweeperInputProxy(private val target: MineSweeperInput) : MineSweeperInput {

    override fun requestWidth(): Int {
        return target.requestWidth()
    }

    override fun requestHeight(): Int {
        return target.requestHeight()
    }

    override fun requestMineCapacity(): Int {
        return target.requestMineCapacity()
    }
}
