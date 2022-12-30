package minesweeper.view

class FakeInputView(private val height: Int, private val width: Int, private val mineCount: Int) : InputView {
    override fun readHeight(): Int = height

    override fun readWidth(): Int = width

    override fun readMineCount(): Int = mineCount
}
