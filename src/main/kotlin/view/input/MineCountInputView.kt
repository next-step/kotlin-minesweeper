package view.input

import view.output.NewLineOutputView

class MineCountInputView : InputView<Int>() {
    override val message: String = "지뢰는 몇 개인가요?"
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
