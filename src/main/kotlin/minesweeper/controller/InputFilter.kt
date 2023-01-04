package minesweeper.controller

import minesweeper.domain.MineCount
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.pos.Position
import minesweeper.view.InputView

object InputFilter {
    fun inputPosition(message: String): Position {
        return try {
            println(message)
            val position = InputParser.parsePosition(readln())
            Position(position)
        } catch (e: Exception) {
            e.message?.let(InputView::printError)
            inputPosition(message)
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
            Coordinate(Position(coordinate.first), Position(coordinate.second))
        } catch (e: Exception) {
            e.message?.let(InputView::printError)
            inputCoordinate(message)
        }
    }
}
