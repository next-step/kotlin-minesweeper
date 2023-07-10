package view

import domain.MineMatrix

class MineGameFlow {
    private lateinit var mineMatrix: MineMatrix

    private val inputView = InputView()
    private val outputView = OutputView()

    fun start() {
        with(inputView) {
            readyMineMatrix(inputHeight(), inputWidth(), inputMineCount())
        }

        with(outputView) {
            outputGameStartMessage()
            val cells = mineMatrix.allCells()
            outputCells(cells)
        }
    }

    private fun readyMineMatrix(height: Int, width: Int, mineCount: Int) {
        mineMatrix = MineMatrix(height = height, width = width, mineCount = mineCount)
    }
}
