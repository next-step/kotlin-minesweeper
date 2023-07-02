package view.input

import domain.PositiveNumber
import view.output.NewLineOutputView

class WidthInputView : InputView<Int, PositiveNumber>() {
    override val message: String = "너비를 입력하세요."
    override val value: PositiveNumber

    init {
        renderMessage()
        value = PositiveNumber(readValue())
        NewLineOutputView()
    }

    override fun readValue(): Int {
        return readln().toInt()
    }
}
