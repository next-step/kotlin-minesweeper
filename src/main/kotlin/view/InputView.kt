package view

object InputView {
    private const val ERR_MSG_INVALID_NUMERIC_FORMAT = "입력된 값의 포맷이 숫자가 압니다."
    private const val ERR_MSG_MINIMUM_SIZE = "최소 1 이상을 입력 해야 합니다."
    private const val TEXT_INPUT_HEIGHT = "높이를 입력하세요."
    private const val TEXT_INPUT_WEIGHT = "너비를 입력하세요."
    private const val TEXT_INPUT_MINE = "지뢰는 몇 개 인가요?"

    fun inputHeight(): Int {
        println(TEXT_INPUT_HEIGHT)
        val inputData = readln()
        validateInputData(inputData)
        return inputData.toInt()
    }

    fun inputWidth(): Int {
        println(TEXT_INPUT_WEIGHT)
        val inputData = readln()
        validateInputData(inputData)
        return inputData.toInt()
    }

    fun inputMineCnt(): Int {
        println(TEXT_INPUT_MINE)
        val inputData = readln()
        validateInputData(inputData)
        return inputData.toInt()
    }

    private fun validateInputData(inputData: String) {
        validateNumericFormat(inputData)
        validateMinimumValue(inputData.toInt())
    }

    private fun validateNumericFormat(inputData: String) {
        require(inputData.toIntOrNull() != null) { ERR_MSG_INVALID_NUMERIC_FORMAT }
    }

    private fun validateMinimumValue(inputData: Int) {
        require(inputData > 0) { ERR_MSG_MINIMUM_SIZE }
    }
}
