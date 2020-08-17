fun main() {
    val (row, col) = requestRowAndCol()
    val boardSize = BoardSize(row, col)
    val mineCount = NumberOfMine(
        number = InputView.requestInputByMode(InputView.Mode.MINE_COUNT),
        boardSize = boardSize
    )
}

private fun requestRowAndCol() =
    LengthOfSide(InputView.requestInputByMode(InputView.Mode.ROW)) to
        LengthOfSide(InputView.requestInputByMode(InputView.Mode.COL))
