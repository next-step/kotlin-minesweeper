package minesweeper.domain

import io.kotest.assertions.assertSoftly
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class MineMapTest {
    /*
    *       *(closed) C(closed) C(closed)
    *       C(closed) *(closed) *(closed)
    *       C(closed) C(closed) C(closed)
    */
    private val mapValues = mapOf(
        Position(1, 1) to Mine(),
        Position(1, 2) to Empty(),
        Position(1, 3) to Empty(),
        Position(2, 1) to Empty(),
        Position(2, 2) to Mine(),
        Position(2, 3) to Mine(),
        Position(3, 1) to Empty(),
        Position(3, 2) to Empty(),
        Position(3, 3) to Empty()
    )

    @Test
    fun `Position(key)와 Cell(value)로 구쉉된 map을 받으면 MineMap을 생성할 수 있다`() {
        // given, when
        val mineMap = MineMap(mapValues)

        // then
        assertThat(mineMap.size).isEqualTo(mapValues.size)
    }

    @Test
    fun `지뢰가 아닌 cell을 클릭하면 isEmptyCellClicked 함수는 주변 cell 상태를 open으로 변경하고 true를 반환한다`() {
        // given
        val mineMap = MineMap(mapValues)
        val position = Position(1, 2)

        // when
        val isEmptyCellClicked = mineMap.isEmptyCellClicked(position)

        // then
        /*
        *       *(opened) C(clicked) C(opened)
        *       C(opened) *(closed ) *(opened)
        *       C(closed) C(closed ) C(closed)
        */
        assertSoftly {
            assertThat(isEmptyCellClicked).isTrue()
            assertThat(mineMap.getCell(Position(1, 1)).openState).isEqualTo(OpenState.OPENED)
            assertThat(mineMap.getCell(Position(1, 2)).openState).isEqualTo(OpenState.OPENED)
            assertThat(mineMap.getCell(Position(1, 3)).openState).isEqualTo(OpenState.OPENED)
            assertThat(mineMap.getCell(Position(2, 1)).openState).isEqualTo(OpenState.OPENED)
            assertThat(mineMap.getCell(Position(2, 2)).openState).isEqualTo(OpenState.OPENED)
            assertThat(mineMap.getCell(Position(2, 3)).openState).isEqualTo(OpenState.OPENED)
            assertThat(mineMap.getCell(Position(3, 1)).openState).isEqualTo(OpenState.CLOSED)
            assertThat(mineMap.getCell(Position(3, 2)).openState).isEqualTo(OpenState.CLOSED)
            assertThat(mineMap.getCell(Position(3, 3)).openState).isEqualTo(OpenState.CLOSED)
        }
    }

    @Test
    fun `지뢰가 심어진 cell을 클릭하면 isEmptyCellClicked 함수는 false를 반환한다`() {
        // given
        val mineMap = MineMap(mapValues)
        val position = Position(1, 1)

        // when
        val isEmptyCellClicked = mineMap.isEmptyCellClicked(position)

        // then
        assertThat(isEmptyCellClicked).isFalse()
    }

    @Test
    fun `주어진 위치 정보에 놓인 Cell 객체를 가져올 수 있다`() {
        // given
        val mineMap = MineMap(mapValues)

        // when
        val cell = mineMap.getCell(Position(1, 1))

        // then
        assertThat(cell).isEqualTo(Mine())
    }

    @Test
    fun `MineMap에 없는 위치 정보를 통해 Cell 객체를 가져온다면 IllegalArgumentException이 발생한다`() {
        // given
        val mineMap = MineMap(mapValues)

        assertThatIllegalArgumentException().isThrownBy { // then
            mineMap.getCell(Position(4, 4)) // when
        }.withMessage("해당 위치에 셀이 없습니다")
    }
}
