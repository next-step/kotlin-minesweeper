package minesweeper

import minesweeper.controller.MinesWeeperController

fun main() {
    val minesWeeperApplication = MinesWeeperApplication()
    minesWeeperApplication.run()
}

class MinesWeeperApplication {
    fun run() {
        val minesWeeperController = MinesWeeperController()
        minesWeeperController.start()
    }
}
