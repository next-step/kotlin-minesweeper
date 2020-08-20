import domain.Board
import domain.Location
import domain.Location.Companion.toLocationOrNull
import view.InputView
import view.ResultView

class Minesweeper(private val board: Board) {

    fun start() {
        ResultView.printStart(board)
        while (true) {
            val location = inputLocation()
            board.open(location)
            ResultView.printBoard(board)
        }
    }

    private tailrec fun inputLocation(): Location {
        val input = InputView.readLocation()?.toLocationOrNull()
        if (input == null) {
            InputView.invalidLocation()
            return inputLocation()
        }
        return input
    }
}
