import controller.MineController
import view.InputView

/**
 * Created by Jaesungchi on 2022.06.28..
 */

fun main() {
    val inputView = InputView(::readlnOrNull)
    MineController(inputView).startMineSweeper()
}
