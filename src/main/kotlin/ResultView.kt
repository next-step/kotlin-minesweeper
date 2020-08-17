object ResultView {

    fun printBoard(board: Board, row: LengthOfSide) {
        println("지뢰 찾기 게임 시작")
        board.grid.chunked(row.length).map {
            println(it.joinToString())
        }
    }
}
