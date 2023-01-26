package view

class OutputView {
    fun showBoard(board: List<String>, width: Int) {
        println("지뢰찾기 게임 시작")
        board.forEachIndexed { index, tile ->
            print("$tile ")
            addLine(index, width)
        }
    }

    private fun addLine(index: Int, width: Int) {
        if ((index + 1) % width == 0) {
            println()
        }
    }
}
