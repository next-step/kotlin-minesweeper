package minesweeper.view.input

import minesweeper.model.board.Board
import minesweeper.model.board.coordinate.Position
import minesweeper.view.input.parser.PositionInputParser

class ConsoleInputView : InputView {
    override fun postionToOpen(board: Board): Position {

        val positionInputParser = PositionInputParser(board)
        return ConsoleReader.read(
            message = "open:",
            inputAtNewLine = false,
            parser = positionInputParser
        )
    }
}
