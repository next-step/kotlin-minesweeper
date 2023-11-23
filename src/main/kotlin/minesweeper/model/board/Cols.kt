package minesweeper.model.board

import kotlin.random.Random

data class Cols(val value: Int) {
    init {
        require(value > 0) { "입력 값은 양수여야 합니다." }
    }

    fun getPosition() = Random.nextInt(value)
}
