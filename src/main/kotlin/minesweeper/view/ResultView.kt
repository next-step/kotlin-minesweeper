package minesweeper.view

object ResultView {

    const val START_ROW = 0

    fun printAllMineGround(markersWithMine: MutableList<List<String>>) {
        (markersWithMine.indices).map { x ->
            printMineGround(markersWithMine, x)
            println()
        }
    }

    private fun printMineGround(markersWithMine: MutableList<List<String>>, column: Int) {
        (markersWithMine[START_ROW].indices).map { row ->
            print(markersWithMine[column][row])
        }
    }

    fun printToReceiptVertical() {
        println("높이를 입력하세요.")
    }

    fun printToReceiptHeight() {
        println("세로를 입력하세요.")
    }

    fun printToReceiptCountOfMine() {
        println("지뢰는 몇 개인가요?")
    }

    fun printToStartGame() {
        println("지뢰찾기 게임 시작")
    }
}