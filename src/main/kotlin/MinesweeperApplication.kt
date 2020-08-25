
import model.Square
import view.InputView
import view.ResultView

fun main(args: Array<String>) {
    val inputView = InputView()

    val width = inputView.inputWidth()
    val height = inputView.inputHeight()
    val mine = inputView.inputMine()
    val minePlate = Square(width, height).make()

    val minesweeperGame = MinesweeperGame()
    minesweeperGame.start(minePlate, mine)
    ResultView.printMinePlate(minePlate)
}
