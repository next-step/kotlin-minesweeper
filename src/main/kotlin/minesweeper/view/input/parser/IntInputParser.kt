package minesweeper.view.input.parser

open class IntInputParser(private val range: IntRange = Int.MIN_VALUE..Int.MAX_VALUE) : InputParser<Int> {

    private val isMaxValueNotDefined = this.range.last == Int.MAX_VALUE

    constructor(minValue: Int) : this(minValue..Int.MAX_VALUE)

    override fun parseValue(inputString: String?): ParseResult<Int> =
        when (val parsedInputResult = inputString.parseToInt()) {
            is ParseResult.Error -> ParseResult.Error(ERROR_MESSAGE_NO_NUMBER)
            is ParseResult.Value -> validateResult(parsedInputResult)
        }

    private fun validateResult(parsedIntValue: ParseResult.Value<Int>): ParseResult<Int> {
        if (parsedIntValue.value in range) {
            return parsedIntValue
        }

        return ParseResult.Error(makeErrorMessageForOutOfRange(range))
    }

    private fun makeErrorMessageForOutOfRange(range: IntRange): String {
        if (this.isMaxValueNotDefined) {
            return "최소 ${range.first} 이상 값을 입력해 주세요. "
        }
        return "${range.first} ~ ${range.last} 사이 값을 입력해 주세요."
    }

    companion object {
        private const val ERROR_MESSAGE_NO_NUMBER = "숫자로 입력해 주세요."

        private fun String?.parseToInt(): ParseResult<Int> = runCatching {
            val intValue = this?.trim()?.toInt()
            require(intValue != null)
            ParseResult.Value(intValue)
        }.getOrElse {
            ParseResult.Error(it.message ?: ERROR_MESSAGE_NO_NUMBER)
        }
    }
}
