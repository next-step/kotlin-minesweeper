package minesweeper.interfaces

import minesweeper.common.execute.Executable
import minesweeper.domain.MineGenerator
import minesweeper.domain.board.MineBoard
import minesweeper.interfaces.ui.ComponentDto
import minesweeper.interfaces.ui.InputConsole
import minesweeper.interfaces.ui.OutputConsole

class MineSweeperController(
    private val mineGenerator: MineGenerator
) : Executable {

    override fun execute() {
        val height = InputConsole.queryHeight()
        val width = InputConsole.queryWidth()
        val mineCount = InputConsole.queryMineCount()

        val mineBoard = MineBoard(height = height, width = width, mineCount = mineCount, mineGenerator = mineGenerator)
        val components = mineBoard.components()
        val componentData = components.map { ComponentDto(it.position.x.value, it.position.y.value, it.isMine) }
        OutputConsole.printComponents(componentData, width)
    }
}
