package mineswipper

import mineswipper.domain.map.Field
import mineswipper.domain.map.PositionFactory
import mineswipper.ui.InputManager
import mineswipper.ui.OutputManager

class MineGame(
    private val inputManager: InputManager,
    private val outputManager: OutputManager
) {
    fun start() {
        val height = inputManager.inputHeight()
        val width = inputManager.inputWidth()
        val mine = inputManager.inputMine()

        val field = Field(
            height,
            width,
            PositionFactory.generateMinePositions(height, width, mine)
        )

        outputManager.printStartGame()
        outputManager.printField(field)
    }

}


fun main() {
    MineGame(InputManager(), OutputManager()).start()
}