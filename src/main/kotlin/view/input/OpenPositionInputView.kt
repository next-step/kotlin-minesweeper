package view.input

import ErrorCode
import domain.Position

class OpenPositionInputView : InputView<Pair<Int, Int>, Position>() {
    override val message: String = "open: "
    override val value: Position

    init {
        print(message)
        value = readValue().run {
            Position.fromInt(first, second)
        }
    }

    override fun readValue(): Pair<Int, Int> {
        val numbers = readln().split(DELIMITER).map { it.toInt() }
        require(numbers.size == 2) { ErrorCode.INVALID_POSITION_FROM_INPUT_ERROR.msg }
        return numbers[0] to numbers[1]
    }

    companion object {
        const val DELIMITER = ","
    }
}
