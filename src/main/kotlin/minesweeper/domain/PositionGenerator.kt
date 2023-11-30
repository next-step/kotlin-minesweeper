package minesweeper.domain

abstract class PositionGenerator(
    protected val height: Size,
    protected val width: Size,
) {

    fun init(): List<Position> {
        return height.getNumbers()
            .flatMap { rowNum ->
                width.getRows(rowNum)
            }.toList()
    }

    fun checkValidCount(count: Size) {
        require((height * width).value >= count.value) { "높이와 너비를 곱한 값보다 더 큰 값을 입력할 수는 없습니다." }
    }

    abstract fun generate(count: Size): List<Position>
}
