package model

class Number(val value: Int) {
    constructor(value: String) : this(value.toInt()) {
        if (!NUMBER_REGEX.matches(value)) {
            throw IllegalArgumentException("not acceptd not number value")
        }
    }

    companion object {
        val NUMBER_REGEX = Regex(pattern = "^?[0-9]\\d*(\\.\\d+)?\$")
    }
}
