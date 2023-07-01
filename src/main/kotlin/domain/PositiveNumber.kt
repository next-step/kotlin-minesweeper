package domain

@JvmInline
value class PositiveNumber(val value: Int) {

    init {
        require(validate()) { ErrorCode.NOT_POSITIVE_NUMBER_ERROR.msg }
    }

    private fun validate(): Boolean {
        return Integer.signum(value) == POSITIVE_NUMBER_RESULT
    }

    companion object {
        const val POSITIVE_NUMBER_RESULT = 1
    }
}
