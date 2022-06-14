import view.InputView
import view.PrintView
import kotlin.random.Random

class MineController {

    fun run() {
        val height = getHeightFromUser()
        val width = getWidthFromUser()
        val mine = getMineCountFromUser()

        val mines = makeMinePosition(height, width, mine)
        printMineBoard(height, width, mines)
    }

    //TODO 지뢰 위치를담는 클래스 만들기
    //TODO 지뢰들을 담는 클래스 만들기
    //TODO 지뢰 위치를 만드는 역할 분리
    private fun makeMinePosition(height: Int, width: Int, mineCount: Int): Set<Pair<Int, Int>> {
        return List(mineCount) {
            val row = Random.nextInt(height)
            val col = Random.nextInt(width)
            row to col
        }.toSet()
    }

    private fun printMineBoard(boardHeight: Int, boardWidth: Int, mines: Set<Pair<Int, Int>>) {
        repeat(boardHeight) { height ->
            repeat(boardWidth) { width ->
                if (mines.contains(height to width)) {
                    print("X")
                } else {
                    print("O")
                }
                print(" ")
            }
            println()
        }
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