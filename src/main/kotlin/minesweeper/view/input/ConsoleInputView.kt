package minesweeper.view.input

import minesweeper.model.board.Board
import minesweeper.model.coordinate.Coordinate
import minesweeper.view.input.parser.PositionInputParser

object ConsoleInputView : InputView {

    private const val INPUT_GUIDE_MESSAGE = "Open cell(row,column): "
    override fun coordinateToOpen(board: Board): Coordinate {
        val positionInputParser = PositionInputParser(board)
        return ConsoleReader.read(
            message = INPUT_GUIDE_MESSAGE,
            inputAtNewLine = false,
            parser = positionInputParser
        )
    }
}
