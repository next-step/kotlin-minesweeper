package domain

class PositiveNumber(injectValue: Any) {
    val value: Int

    init {
        require(validate(injectValue)) { ErrorCode.NOT_POSITIVE_NUMBER_ERROR.msg }
        value = injectValue as Int
    }

    private fun validate(injectValue: Any): Boolean {
        return Integer.signum(injectValue as Int) == POSITIVE_NUMBER_RESULT
    }

    companion object {
        const val POSITIVE_NUMBER_RESULT = 1
    }
}
