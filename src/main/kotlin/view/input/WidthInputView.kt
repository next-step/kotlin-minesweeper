package view.input

import view.output.NewLineOutputView

class WidthInputView : InputView<Int>() {
    override val message: String = "너비를 입력하세요."
    override val value: Int

    init {
        renderMessage()
        value = readValue()
        NewLineOutputView()
    }

    override fun readValue(): Int {
        return readln().toInt()
    }
}
