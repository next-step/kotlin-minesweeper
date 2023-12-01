package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class MineMapTest {
    private val mapValues = mapOf(
        Position(1, 1) to Mine,
        Position(1, 2) to Empty(),
        Position(2, 1) to Empty(),
        Position(2, 2) to Mine
    )

    @Test
    fun `minePositions와 emptyPositions이 주어질 때 MineMap을 생성할 수 있다`() {
        // given, when
        val mineMap = MineMap(mapValues)

        // then
        assertThat(mineMap.size).isEqualTo(4)
    }

    @Test
    fun `주어진 위치 정보에 놓인 Cell 객체를 가져올 수 있다`() {
        // given, when
        val mineMap = MineMap(mapValues)

        // when
        val cell = mineMap.getCell(Position(1, 1))

        // then
        assertThat(cell).isEqualTo(Mine)
    }

    @Test
    fun `MineMap에 없는 위치 정보를 통해 Cell 객체를 가져온다면 IllegalArgumentException이 발생한다`() {
        // given, when
        val mineMap = MineMap(mapValues)

        assertThatIllegalArgumentException().isThrownBy { // then
            mineMap.getCell(Position(3, 3)) // when
        }.withMessage("해당 위치에 셀이 없습니다")
    }
}
