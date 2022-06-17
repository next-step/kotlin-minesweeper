package view

import domain.MinePosition


object InputView {
    private const val WRONG_INPUT_MESSAGE_FOR_INT = "숫자만 사용할 수 있습니다"
    private const val DESC_FOR_POSITION = "open: "
    private const val POSITION_DELIMITER = ","
    private const val POSITION_SIZE = 2
    private const val ERROR_MSG_FOR_WRONG_POSITION_SIZE = "위치는 (1, 1) 형식으로 넣어주세요"

    fun getIntOrThrow(): Int {
        val input = readln()
        return input.toIntOrNull() ?: throw Exception(WRONG_INPUT_MESSAGE_FOR_INT)
    }

    fun getPositionWithPositionDesc(): MinePosition {
        print(DESC_FOR_POSITION)
        val inputs = readln().split(POSITION_DELIMITER)

        require(inputs.size == POSITION_SIZE) { ERROR_MSG_FOR_WRONG_POSITION_SIZE }

        val row = inputs[0].toIntOrNull() ?: throw Exception(WRONG_INPUT_MESSAGE_FOR_INT)
        val col = inputs[1].toIntOrNull() ?: throw Exception(WRONG_INPUT_MESSAGE_FOR_INT)

        return MinePosition(row, col)
    }
}
