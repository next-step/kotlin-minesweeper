package domain

@JvmInline
value class MineNumber private constructor(val number: Int) {
    constructor() : this(DEFAULT_NUMBER)

    operator fun inc() = MineNumber(number + ONE)

    companion object {
        private const val DEFAULT_NUMBER = 0
        private const val ONE = 1
    }
}
