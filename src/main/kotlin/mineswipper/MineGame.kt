package mineswipper

import mineswipper.domain.map.Field
import mineswipper.domain.map.position.Size
import mineswipper.domain.map.util.PositionFactory
import mineswipper.domain.map.util.RandomMinePositionGenerator
import mineswipper.ui.InputManager
import mineswipper.ui.OutputManager

class MineGame(
    private val inputManager: InputManager,
    private val outputManager: OutputManager
) {
    fun start() {
        val positionFactory = PositionFactory(RandomMinePositionGenerator())
        val height = inputManager.inputHeight()
        val width = inputManager.inputWidth()
        val size = Size(width, height)
        val mine = inputManager.inputMine()
        val field = Field(
            size,
            positionFactory.generateMinePositions(size, mine)
        )

        outputManager.printStartGame()
        outputManager.printField(field)
    }
}

fun main() {
    MineGame(InputManager(), OutputManager()).start()
}
