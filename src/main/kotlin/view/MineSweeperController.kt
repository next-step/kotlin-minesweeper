package view

import domain.map.MineMapFactory

class MineSweeperController(
    private val inputView: MineSweeperInputView,
    private val resultView: MineSweeperResultView,
    private val mineMapFactory: MineMapFactory,
) {

    fun start() {
        val mineSweeperInitProperty = inputView.readInitProperty()
        val mineMap = mineMapFactory.create(mineSweeperInitProperty)
        resultView.display(mineMap)
    }
}
