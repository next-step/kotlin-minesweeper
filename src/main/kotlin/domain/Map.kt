package domain

import domain.builder.map
import java.util.*

class Map(val rows: SortedSet<Row>) : SortedSet<Row> by rows {
    init {
        require(rows.all { it.size == first().size }) { "맵에 포함된 행들의 크기는 모두 동일해야 합니다." }
        require(rows.all { first().getY() == START_ROW }) { "맵의 행들의 y값은 ${START_ROW}부터 시작해야 합니다." }
        require(rows.size >= MIN_SIZE) { "맵에는 최소 1개 이상의 행이 존재해야 합니다." }
        require(rows.size <= MAX_SIZE) { "맵에는 최대 20개 이하의 행이 포함되어야 합니다." }
        require(checkRowDiffIsOne()) { "행들의 y 값은 1씩 커져야합니다" }
    }

    constructor(vararg rows: Row) : this(rows.toSortedSet())

    private fun checkRowDiffIsOne(): Boolean {
        return map { it.getY() } == (first().getY()..last().getY()).toList()
    }

    companion object {
        private const val MIN_SIZE = 1
        private const val MAX_SIZE = 20
        private const val START_ROW = 0

        fun of(
            height: Int,
            width: Int,
            mineCount: Int,
            mineGenerator: MineGenerator = RandomMineGenerator(width, height)
        ): Map {
            return map {
                (0 until height).map { y ->
                    row {
                        (0 until width).map { x ->
                            col {
                                coordinate(x = x, y = y)
                            }
                        }
                    }
                }
                mines { mineGenerator.generate(mineCount) }
            }
        }
    }
}
