import ramdom.RandomLogic
import view.InputView
import view.OutputView

fun main() {
    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val mineCnt = InputView.inputMineCnt()
    Validate.validateBoardInfo(width, height, mineCnt)

    val board = CreateFactory.createBoard(width = width, height = height)
    val mineSearch = MineSearch(board, RandomLogic())
    mineSearch.settingMines(mineCnt)

    OutputView.drawBoard(mineSearch.board)
}
