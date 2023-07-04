package domain.cell

@JvmInline
value class AroundMineCount(val value: Int) {

    init {
        require(value >= 0) {
            "around mine count must be greater than or equal to zero"
        }
    }

    fun isZero(): Boolean {
        return value == 0
    }
}
