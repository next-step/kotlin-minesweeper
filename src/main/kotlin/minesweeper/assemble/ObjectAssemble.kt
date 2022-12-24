package minesweeper.assemble

import minesweeper.common.execute.Executable
import minesweeper.domain.RandomMineGenerator
import minesweeper.interfaces.MineSweeperController

object ObjectAssemble {

    fun executableApp(): Executable = mineSweeperController()
    private fun mineSweeperController() = MineSweeperController(mineGenerator = mineGenerator())
    private fun mineGenerator() = RandomMineGenerator
}
