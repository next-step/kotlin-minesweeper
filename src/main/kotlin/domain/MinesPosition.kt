package domain

class MinesPosition(
    private val value: List<Position>,
) {

    val size = value.size

    operator fun contains(position: Position): Boolean {
        return value.contains(position)
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
