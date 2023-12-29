class RandomMinePositionsGenerator(
    private val maxWidth: Int,
    private val maxHeight: Int,
) {
    fun generate(size: Int): List<Position> {
        require(size <= maxWidth * maxHeight) { "생성할 수 있는 지뢰 위치 갯수보다 많이 생성하려합니다." }

        return (0 until maxWidth).flatMap { x ->
            (0 until maxHeight).map { y ->
                Position(y, x)
            }
        }.shuffled()
        .subList(0, size)
    }
}
