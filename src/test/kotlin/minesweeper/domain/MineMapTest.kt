package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class MineMapTest {

    /**
     *
     *   1  *  1  0
     *   2  2  2  1
     *   1  *  3  *
     *   1  2  *  2
     */
    @Test
    fun `position과 mine 여부가 주어지면 Board를 생성한다`() {
        val mineMap = MineMap(
            mapOf(
                Position.from(1, 1) to false,
                Position.from(1, 2) to true,
                Position.from(1, 3) to false,
                Position.from(1, 4) to false,
                Position.from(2, 1) to false,
                Position.from(2, 2) to false,
                Position.from(2, 3) to false,
                Position.from(2, 4) to false,
                Position.from(3, 1) to false,
                Position.from(3, 2) to true,
                Position.from(3, 3) to false,
                Position.from(3, 4) to true,
                Position.from(4, 1) to false,
                Position.from(4, 2) to false,
                Position.from(4, 3) to true,
                Position.from(4, 4) to false,
            )
        )

        val result = mineMap.getBoard()

        with(result.cells) {
            assertAll(
                { assertThat(getCell(1, 1).aroundMineCount.value).isEqualTo(1) },
                { assertThat(getCell(1, 2).isMine()).isTrue },
                { assertThat(getCell(1, 3).aroundMineCount.value).isEqualTo(1) },
                { assertThat(getCell(1, 4).aroundMineCount.value).isEqualTo(0) },
                { assertThat(getCell(2, 1).aroundMineCount.value).isEqualTo(2) },
                { assertThat(getCell(2, 2).aroundMineCount.value).isEqualTo(2) },
                { assertThat(getCell(2, 3).aroundMineCount.value).isEqualTo(3) },
                { assertThat(getCell(2, 4).aroundMineCount.value).isEqualTo(1) },
                { assertThat(getCell(3, 1).aroundMineCount.value).isEqualTo(1) },
                { assertThat(getCell(3, 2).isMine()).isTrue },
                { assertThat(getCell(3, 3).aroundMineCount.value).isEqualTo(3) },
                { assertThat(getCell(3, 4).isMine()).isTrue },
                { assertThat(getCell(4, 1).aroundMineCount.value).isEqualTo(1) },
                { assertThat(getCell(4, 2).aroundMineCount.value).isEqualTo(2) },
                { assertThat(getCell(4, 3).isMine()).isTrue },
                { assertThat(getCell(4, 4).aroundMineCount.value).isEqualTo(2) },
            )
        }
    }

    /**
     *
     *   *  *  1  0
     *   2  2  2  1
     *   0  1  2  *
     *   0  1  *  2
     */
    @Test
    fun `position과 mine 여부가 주어지면 Board를 생성한다2`() {
        val mineMap = MineMap(
            mapOf(
                Position.from(1, 1) to true,
                Position.from(1, 2) to true,
                Position.from(1, 3) to false,
                Position.from(1, 4) to false,
                Position.from(2, 1) to false,
                Position.from(2, 2) to false,
                Position.from(2, 3) to false,
                Position.from(2, 4) to false,
                Position.from(3, 1) to false,
                Position.from(3, 2) to false,
                Position.from(3, 3) to false,
                Position.from(3, 4) to true,
                Position.from(4, 1) to false,
                Position.from(4, 2) to false,
                Position.from(4, 3) to true,
                Position.from(4, 4) to false,
            )
        )

        val result = mineMap.getBoard()

        with(result.cells) {
            assertAll(
                { assertThat(getCell(1, 1).isMine()).isTrue },
                { assertThat(getCell(1, 2).isMine()).isTrue },
                { assertThat(getCell(1, 3).aroundMineCount.value).isEqualTo(1) },
                { assertThat(getCell(1, 4).aroundMineCount.value).isEqualTo(0) },
                { assertThat(getCell(2, 1).aroundMineCount.value).isEqualTo(2) },
                { assertThat(getCell(2, 2).aroundMineCount.value).isEqualTo(2) },
                { assertThat(getCell(2, 3).aroundMineCount.value).isEqualTo(2) },
                { assertThat(getCell(2, 4).aroundMineCount.value).isEqualTo(1) },
                { assertThat(getCell(3, 1).aroundMineCount.value).isEqualTo(0) },
                { assertThat(getCell(3, 2).aroundMineCount.value).isEqualTo(1) },
                { assertThat(getCell(3, 3).aroundMineCount.value).isEqualTo(2) },
                { assertThat(getCell(3, 4).isMine()).isTrue },
                { assertThat(getCell(4, 1).aroundMineCount.value).isEqualTo(0) },
                { assertThat(getCell(4, 2).aroundMineCount.value).isEqualTo(1) },
                { assertThat(getCell(4, 3).isMine()).isTrue },
                { assertThat(getCell(4, 4).aroundMineCount.value).isEqualTo(2) },
            )
        }
    }

    private fun Map<Position, Cell>.getCell(row: Int, column: Int): Cell {
        return getValue(Position.from(row = row, column = column))
    }
}
