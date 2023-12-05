import map.Board
import map.MapInfo
import ramdom.MineRandomLogic
import view.InputView
import view.OutputView

fun main() {
    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val mineCnt = InputView.inputMineCnt()
    val mapInfo = MapInfo(height, width, mineCnt)

    val board = Board(mapInfo, MineRandomLogic())
    OutputView.drawBoard(board)
}
