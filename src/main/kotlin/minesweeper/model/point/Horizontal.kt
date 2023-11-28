package minesweeper.model.point

@JvmInline
value class Horizontal(
    private val value: Int,
) {
    fun moveTo(delta: Int): Horizontal {
        return Horizontal(value + delta)
    }

    fun movePossible(delta: Int, horizontalLimit: Int): Boolean {
        return (this.value + delta) in 0..horizontalLimit
    }

    init {
        require(value >= 0) { "Horizontal 에 입력된 ${value}으로 생성이 불가능합니다. 0 혹은 양의 정수만 허용됩니다" }
    }
}
