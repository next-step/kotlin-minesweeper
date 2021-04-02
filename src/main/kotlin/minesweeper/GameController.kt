package minesweeper

fun main() {
    val gameBoard = createGameBoard()

    println("지뢰찾기 게임 시작")
    OutputView.drawBoard(gameBoard)
}

fun createGameBoard(): MineBoard {
    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val mineNumber = InputView.inputNumberOfMine()

    return MineBoard(width, height, mineNumber)
}