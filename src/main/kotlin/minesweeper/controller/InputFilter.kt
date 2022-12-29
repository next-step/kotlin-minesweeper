package minesweeper.controller

import minesweeper.domain.MineCount
import minesweeper.domain.tile.pos.Position
import minesweeper.view.InputView

object InputFilter {
    fun inputPosition(message: String): Position {
        return try {
            println(message)
            val position = InputParser.parsePosition(InputView.inputNumber())
            Position(position)
        } catch (e: Exception) {
            e.message?.let { InputView.printError(it) }
            inputPosition(message)
        }
    }

    fun inputMineCount(message: String, maxMineCount: Int): MineCount {
        return try {
            println(message)
            MineCount(InputParser.parseNumber(InputView.inputNumber()), maxMineCount)
        } catch (e: Exception) {
            e.message?.let { InputView.printError(it) }
            inputMineCount(message, maxMineCount)
        }
    }
}
