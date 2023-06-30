package view.input

import view.output.NewLineOutputView

class HeightInputView : InputView<Int>() {
    override val message: String = "높이를 입력하세요."
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
