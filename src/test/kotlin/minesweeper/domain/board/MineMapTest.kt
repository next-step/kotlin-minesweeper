package minesweeper.domain.board

import minesweeper.domain.Position
import minesweeper.domain.cell.BlockCell
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.MineCell
import minesweeper.domain.position.Positions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Suppress("NonAsciiCharacters")
class MineMapTest {

    @ParameterizedTest
    @MethodSource("mineMapSource")
    fun `position과 mine 여부가 주어지면 position에 해당하는 Cell들을 생성한다`(data: TestMineMapData) {
        val mineMap = MineMap(data.mineMap)

        val result = mineMap.getCells()

        assertThat(result).usingRecursiveComparison().isEqualTo(data.expected)
    }

    private fun mineMapSource() = listOf(
        firstData(),
        secondData(),
    )

    /**
     *
     *   1  *  1  0
     *   2  2  3  1
     *   1  *  3  *
     *   1  2  *  2
     */
    private fun firstData(): TestMineMapData {
        val mines = mutableListOf(
            false, true, false, false,
            false, false, false, false,
            false, true, false, true,
            false, false, true, false,
        )
        val expected = mutableListOf(
            1, M, 1, 0,
            2, 2, 3, 1,
            1, M, 3, M,
            1, 2, M, 2,
        )
        val positions = Positions.from(1..4, 1..4).positions
        return TestMineMapData(
            mineMap = positions.associateWith { mines.removeFirst() },
            expected = positions.associateWith { expected.removeFirst().toCell() }
        )
    }

    /**
     *
     *   *  *  1  0
     *   2  2  2  1
     *   0  1  2  *
     *   0  1  *  2
     */
    private fun secondData(): TestMineMapData {
        val mines = mutableListOf(
            true, true, false, false,
            false, false, false, false,
            false, false, false, true,
            false, false, true, false,
        )
        val expected = mutableListOf(
            M, M, 1, 0,
            2, 2, 2, 1,
            0, 1, 2, M,
            0, 1, M, 2,
        )
        val positions = Positions.from(1..4, 1..4).positions
        return TestMineMapData(
            mineMap = positions.associateWith { mines.removeFirst() },
            expected = positions.associateWith { expected.removeFirst().toCell() }
        )
    }

    private fun Int.toCell(): Cell {
        return when (this) {
            M -> MineCell
            else -> BlockCell(MineCount(this))
        }
    }
}

private const val M = -1

data class TestMineMapData(
    val mineMap: Map<Position, Boolean>,
    val expected: Map<Position, Cell>,
)
