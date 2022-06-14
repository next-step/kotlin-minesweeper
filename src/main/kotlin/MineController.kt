import view.InputView
import view.PrintView

class MineController {

    fun run() {
        val height = getHeightFromUser()
        val width = getWidthFromUser()
        val mine = getMineCountFromUser()
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
}