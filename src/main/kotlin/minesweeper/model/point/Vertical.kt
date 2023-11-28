package minesweeper.model.point

@JvmInline
value class Vertical(
    val value: Int,
) {

    init {
        require(value >= 0) { "Vertical 에 입력된 ${value}으로 생성이 불가능합니다. 0 혹은 양의 정수만 허용됩니다" }
    }

    fun moveTo(delta: Int): Vertical {
        return Vertical(value + delta)
    }

    fun movePossible(delta: Int, limit: Vertical): Boolean {
        return (delta + value) in 0..limit.value
    }
}
