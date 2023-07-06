package view.input

import domain.MineCountNumber
import domain.PositiveNumber
import view.output.NewLineOutputView

class MineCountInputView(height: PositiveNumber, width: PositiveNumber) : InputView<Int, MineCountNumber>() {
    override val message: String = "지뢰는 몇 개인가요?"
    override val value: MineCountNumber

    init {
        renderMessage()
        val readValue = readValue()
        value = MineCountNumber(readValue, height, width)
        NewLineOutputView()
    }

    override fun readValue(): Int {
        return readln().toInt()
    }
}
