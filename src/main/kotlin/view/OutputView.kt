package view

class OutputView {
    fun showBoard(board: List<String>, width: Int) {
        println("지뢰찾기 게임 시작")
        for (index in board.indices) {
            print("${board[index]} ")
            if ((index + 1) % width == 0) {
                println()
            }
        }
    }
}
