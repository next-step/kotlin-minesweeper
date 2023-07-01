package view

import domain.map.MineMap

class MineSweeperController(
    private val inputView: MineSweeperInputView,
) {

    fun start() {
        val mineSweeperInitProperty = inputView.readInitProperty()
        val mineMap = MineMap.create(mineSweeperInitProperty)
    }
}
