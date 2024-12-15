import domain.BoardCreator
import domain.RandomMinePlacer
import view.InputView
import view.OutputView

fun main() {
    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val mineCount = InputView.inputMineCount()

    val minePlacer = RandomMinePlacer()
    val boardCreator = BoardCreator(minePlacer)
    val board = boardCreator.create(height, width, mineCount)

    OutputView.notifyGameStart()
    OutputView.printBoard(board)
}
