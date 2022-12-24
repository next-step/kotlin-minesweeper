package minesweeper

import minesweeper.assemble.ObjectAssemble
import minesweeper.common.execute.Executable

class MineSweeperApp(
    private val executableApp: Executable
) : Executable by executableApp

fun main() {
    val executableApp = ObjectAssemble.executableApp()
    val mineSweeperApp = MineSweeperApp(executableApp = executableApp)
    mineSweeperApp.execute()
}
