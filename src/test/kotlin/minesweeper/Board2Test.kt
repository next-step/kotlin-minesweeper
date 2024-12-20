package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.random.Random

class Board2Test {
    @Test
    fun `지뢰 갯수 지정 가능하다`() {
        val dimensions = Dimensions(3, 3)
        val board = Board2(dimensions, setOf(Position(0, 0), Position(0, 2)))

        assertAll(
            { assertThat(board.checkMine(Position(0, 0))).isTrue() },
            { assertThat(board.checkMine(Position(0, 1))).isFalse() },
            { assertThat(board.checkMine(Position(0, 2))).isTrue() },
        )
    }

    @Test
    fun `지뢰 최소개수시 예외 발생`() {
        assertThatIllegalArgumentException().isThrownBy {
            Board2(Dimensions(3, 3), emptySet())
        }
    }

    @Test
    fun `지뢰 최대개수 초과시 예외 발생`() {
        assertThatIllegalArgumentException().isThrownBy {
            Board2(Dimensions(2, 2), setOf(Position(0, 0), Position(0, 1), Position(1, 0), Position(1, 1), Position(2, 1)))
        }
    }
}

class MinePlacerTest {
    @Test
    fun `지뢰 갯수 지정 가능하다`() {
        val dimensions = Dimensions(3, 3)
        val minePlacer = MinePlacer(dimensions, 2)

        val minePositions = minePlacer.placeMines()

        assertThat(minePositions).hasSize(2)
    }
}


class Board2(
    private val dimensions: Dimensions,
    minePositions: Set<Position>
) {
    init {
        require(minePositions.isNotEmpty()) { "마인은 최소 ${MIN_MINE_COUNT}개 이상 이어야 합니다." }
        require(minePositions.size < dimensions.totalCells) { "마인의 수는 전체 셀 수보다 작아야 합니다." }
    }

    private val cells: Cells = initializeCells(minePositions)

    private fun initializeCells(minePositions: Set<Position>): Cells {
        val cellList = dimensions.allPositions().map { position ->
            if (minePositions.contains(position)) {
                Cell.createMine(position)
            } else {
                Cell.createDefault(position)
            }
        }
        return Cells.create(cellList)
    }

    fun checkMine(position: Position): Boolean {
        return cells.checkMine(position)
    }

    companion object {
        const val MIN_MINE_COUNT = 1
    }
}

class MinePlacer(
    private val dimensions: Dimensions,
    private val mineCount: Int
) {
    init {
        require(mineCount in 1 until dimensions.totalCells) {
            "Mine count must be less than total cells and greater than 0."
        }
    }

    fun placeMines(): Set<Position> {
        val positions = mutableSetOf<Position>()
        while (positions.size < mineCount) {
            val x = Random.nextInt(dimensions.width)
            val y = Random.nextInt(dimensions.height)
            positions.add(Position(x, y))
        }
        return positions
    }
}