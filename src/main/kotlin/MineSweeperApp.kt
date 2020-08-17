fun main() {
    val (row, col) = requestRowAndCol()
}

private fun requestRowAndCol() =
    LengthOfSide(InputView.requestInputByMode(InputView.Mode.ROW)) to
        LengthOfSide(InputView.requestInputByMode(InputView.Mode.COL))
