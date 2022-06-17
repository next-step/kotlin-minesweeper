import domain.RandomMineGenerator
import view.InputView
import view.PrintView

fun main() {

    val height = getHeightFromUser()
    val width = getWidthFromUser()
    val mine = getMineCountFromUser()

    val mineGenerator = RandomMineGenerator(height, width)

    val mineController = MineController(
        height = height,
        width = width,
        mine = mine,
        mineGenerator = mineGenerator
    )

    mineController.run()
}

private fun getHeightFromUser(): Int {
    PrintView.printHeightMessage()

    return InputView.getIntOrThrow()
}

private fun getWidthFromUser(): Int {
    PrintView.printWidthMessage()

    return InputView.getIntOrThrow()
}

private fun getMineCountFromUser(): Int {
    PrintView.printMineMessage()

    return InputView.getIntOrThrow()
}
