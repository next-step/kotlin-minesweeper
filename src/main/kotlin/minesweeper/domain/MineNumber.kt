package minesweeper.domain

@JvmInline
value class MineNumber(private val number: String) {
    init {
        require(number.toIntOrNull() != null) {
            "지뢰 갯수는 숫자이어야함"
        }

        require(number.toInt () > MINIMUIM_NUMBER) {
            "지뢰 갯수는 0보다 커야함"
        }
    }

    fun getMineNumber(): Int {
        return number.toInt()
    }

    companion object {
        const val MINIMUIM_NUMBER = 0
    }
}
