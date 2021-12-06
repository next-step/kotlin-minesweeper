package domain

class MinesPosition(
    private val value: List<Position>,
) {

    private val aroundMineCountByPosition: Map<Position, Int> = value
        .flatMap(Position::getAroundPositions)
        .fold(emptyMap()) { acc, (x, y) ->
            val position = Position(x, y)
            val mineCount = (acc[position] ?: 0) + 1
            acc + (position to mineCount)
        }
    val size = value.size

    operator fun contains(position: Position): Boolean {
        return value.contains(position)
    }

    fun getMineCountByPosition(position: Position): Int {
        return aroundMineCountByPosition[position] ?: 0
    }

    companion object {
        private const val START = 0

        fun generate(
            maxWidth: Width,
            maxHeight: Height,
            generateCount: Int,
        ): MinesPosition {
            require((maxWidth.value * maxHeight.value) >= generateCount) { "마인의 개수는 전체 셀의 개수보다 많을 수 없습니다." }

            val positions = mutableListOf<Position>()
            val widthRange = START until maxWidth.value
            val heightRange = START until maxHeight.value

            while (positions.size != generateCount) {
                val x = widthRange.random()
                val y = heightRange.random()
                val position = Position(x, y)

                positions
                    .takeIf { !it.contains(position) }
                    ?.add(position)
            }

            return MinesPosition(positions.toList())
        }
    }
}
