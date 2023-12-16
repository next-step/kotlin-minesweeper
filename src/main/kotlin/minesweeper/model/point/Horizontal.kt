package minesweeper.model.point

@JvmInline
value class Horizontal(
    val value: Int,
) {

    init {
        require(value >= 0) { "Horizontal 에 입력된 ${value}으로 생성이 불가능합니다. 0 혹은 양의 정수만 허용됩니다" }
    }

    fun moveTo(delta: Int): Horizontal {
        return Horizontal(value + delta)
    }

    fun movePossible(delta: Int, limit: Horizontal): Boolean {
        return (this.value + delta) in 0 until limit.value
    }

    fun range(): IntRange {
        return 0 until value
    }
}
