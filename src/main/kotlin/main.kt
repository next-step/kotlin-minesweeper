import domain.RandomMineGenerator
import view.InputView
import view.PrintView

fun main() {

    val height = getHeightFromUser()
    val width = getWidthFromUser()
    val mine = getMineCountFromUser()

    val mineController = MineController()
    val mineGenerator = RandomMineGenerator(height, width)

    mineController.run(
        height = height,
        width = width,
        mine = mine,
        mineGenerator = mineGenerator
    )
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
