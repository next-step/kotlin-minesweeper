import domain.MineBoard
import domain.MineFactory
import view.UI

fun main() {
    println("높이를 입력하세요.")
    val height = readln().toInt()

    println("너비를 입력하세요.")
    val width = readln().toInt()

    println("지뢰는 몇 개인가요?")
    val count = readln().toInt()
    val mines = (0 until count).map { MineFactory.of(width, height) }

    println("지뢰찾기 게임 시작")
    val mineBoard = MineBoard(width, height)
    mineBoard.setMines(mines)

    UI.displays(mineBoard.boards)
}
