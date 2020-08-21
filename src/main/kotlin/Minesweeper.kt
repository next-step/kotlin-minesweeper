import domain.Board
import domain.Location
import domain.Location.Companion.toLocationOrNull
import domain.Result
import view.InputView
import view.ResultView

class Minesweeper(private val board: Board) {

    fun start() {
        ResultView.printStart(board)
        var result = Result.PROGRESS
        while (isProgress(result)) {
            val location = inputLocation()
            result = board.open(location)
            ResultView.printBoard(board)
        }
        board.openAll()
        ResultView.printResult(result, board)
    }

    private fun isProgress(result: Result) = result != Result.WIN && result != Result.LOSE

    private tailrec fun inputLocation(): Location {
        val input = InputView.readLocation()?.toLocationOrNull()
        if (input == null) {
            InputView.invalidLocation()
            return inputLocation()
        }
        return input
    }
}
