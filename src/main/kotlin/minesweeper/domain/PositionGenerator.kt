package minesweeper.domain

abstract class PositionGenerator(
    protected val height: Size,
    protected val width: Size,
) {
    abstract fun generate(count: Size): List<Position>
    fun checkValidCount(count: Size) {
        require((height * width).value >= count.value) { "높이와 너비를 곱한 값보다 더 큰 값을 입력할 수는 없습니다." }
    }
}
