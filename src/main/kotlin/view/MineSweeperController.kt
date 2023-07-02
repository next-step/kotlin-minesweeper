package view

import domain.map.MineMap
import domain.mine.MineCoordinatesCreator

class MineSweeperController(
    private val inputView: MineSweeperInputView,
    private val resultView: MineSweeperResultView,
    private val mineCoordinatesCreator: MineCoordinatesCreator,
) {

    fun start() {
        val mineSweeperInitProperty = inputView.readInitProperty()
        val mineMap = MineMap.create(
            mineSweeperInitProperty = mineSweeperInitProperty,
            mineCoordinatesCreator = mineCoordinatesCreator,
        )
        resultView.display(mineMap)
    }
}
