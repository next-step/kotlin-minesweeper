package minesweeper.view.input

import minesweeper.model.board.Board
import minesweeper.model.coordinate.Coordinate
import minesweeper.view.input.parser.PositionInputParser

class ConsoleInputView : InputView {
    override fun coordinateToOpen(board: Board): Coordinate {

        val positionInputParser = PositionInputParser(board)
        return ConsoleReader.read(
            message = INPUT_GUIDE_MESSAGE,
            inputAtNewLine = false,
            parser = positionInputParser
        )
    }

    companion object {
        private const val INPUT_GUIDE_MESSAGE = "Open cell(row,column): "
    }
}
