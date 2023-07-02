package view.input

import domain.PositiveNumber
import view.output.NewLineOutputView

class HeightInputView : InputView<Int, PositiveNumber>() {
    override val message: String = "높이를 입력하세요."
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
