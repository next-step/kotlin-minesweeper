import domain.Board
import domain.Location
import domain.Location.Companion.toLocationOrNull
import domain.Result
import view.InputView
import view.ResultView

class Minesweeper(
    private val width: Int,
    private val height: Int,
    private val board: Board
) {

    fun start() {
        var result = Result.PROGRESS

        while (isProgress(result)) {
            ResultView.printBoard(board, width)
            val location = inputLocation()
            result = board.open(location)
            ResultView.printResult(result)
        }
        close()
    }

    private fun isProgress(result: Result) = result != Result.WIN && result != Result.LOSE

    private fun close() {
        board.openAll()
        ResultView.printBoard(board, width)
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
