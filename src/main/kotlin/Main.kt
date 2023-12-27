import map.Board
import map.MapInfo
import view.InputView
import view.OutputView

fun main() {
    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val mineCnt = InputView.inputMineCnt()

    val mapInfo = MapInfo(height, width, mineCnt)
    val game = MinesWeeperGame(Board(mapInfo), InputView, OutputView)

    game.startGame()
    // OutputView.drawBoard(board)
}
