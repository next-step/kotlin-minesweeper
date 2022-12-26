package minesweeper.controller

import minesweeper.domain.MineLocatorMap
import minesweeper.view.ConsoleInput
import minesweeper.view.ConsoleOutput

class GameController(private val input: ConsoleInput, private val output: ConsoleOutput) {
    fun start() {
        val height = input.getHeight()
        val width = input.getWidth()
        val countOfMine = input.getCountOfMine()

        val mineLocatorMap = MineLocatorMap(width = width, height = height, countOfMine)

        output.printMap(width, mineLocatorMap.sites.map { it.hasMine })
    }
}
