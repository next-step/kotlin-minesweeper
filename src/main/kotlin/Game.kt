import minesweeper.GameBoardSize
import minesweeper.Message
import view.Input
import view.Output

fun main() {
    Output.printAny(Message.INPUT_HEIGHT)
    val height: String = Input.getLine()

    Output.printAny(Message.INPUT_WIDTH)
    val width: String = Input.getLine()

    Output.printAny(Message.INPUT_MINES)
    val mines: String = Input.getLine()
}
