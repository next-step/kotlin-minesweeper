package minesweeper.controller

import minesweeper.domain.MineCount
import minesweeper.domain.land.state.Size
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.view.InputView

object InputFilter {
    fun inputSize(message: String): Size {
        return try {
            println(message)
            val size = InputParser.parseNumber(readln())
            Size(size)
        } catch (e: Exception) {
            e.message?.let(InputView::printError)
            inputSize(message)
        }
    }

    fun inputMineCount(message: String, maxMineCount: Int): MineCount {
        return try {
            println(message)
            MineCount(InputParser.parseNumber(readln()), maxMineCount)
        } catch (e: Exception) {
            e.message?.let(InputView::printError)
            inputMineCount(message, maxMineCount)
        }
    }

    fun inputCoordinate(message: String): Coordinate {
        return try {
            print(message)
            val coordinate = InputParser.parseCoordinate(readln())
            Coordinate.of(coordinate.first, coordinate.second)
        } catch (e: Exception) {
            e.message?.let(InputView::printError)
            inputCoordinate(message)
        }
    }
}
