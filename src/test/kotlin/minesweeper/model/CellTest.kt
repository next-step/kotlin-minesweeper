package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("셀 테스트")
class CellTest {

    @Test
    fun `주변 8개 지뢰의 개수를 찾는 기능이 정상 동작`() {
        // given
        // * 1 0
        // 2 2 1
        // 1 * 1
        val `p(0,0)` = ClosedMine(CellPosition.of(0, 0))
        val `p(1,0)` = ClosedNonMine(CellPosition.of(1, 0))
        val `p(2,0)` = ClosedNonMine(CellPosition.of(2, 0))
        val `p(0,1)` = ClosedNonMine(CellPosition.of(0, 1))
        val `p(1,1)` = ClosedNonMine(CellPosition.of(1, 1))
        val `p(2,1)` = ClosedNonMine(CellPosition.of(2, 1))
        val `p(0,2)` = ClosedNonMine(CellPosition.of(0, 2))
        val `p(1,2)` = ClosedMine(CellPosition.of(1, 2))
        val `p(2,2)` = ClosedNonMine(CellPosition.of(2, 2))

        val board = MineBoard(
            listOf(
                Cells(mutableListOf(`p(0,0)`, `p(1,0)`, `p(2,0)`)),
                Cells(mutableListOf(`p(0,1)`, `p(1,1)`, `p(2,1)`)),
                Cells(mutableListOf(`p(0,2)`, `p(1,2)`, `p(2,2)`))
            )
        )

        // when, then
        assertAll(
            "find surrounding mine count sum test",
            { assertThat(`p(1,0)`.findSurroundingMineCountSum(board)).isEqualTo(1) },
            { assertThat(`p(2,0)`.findSurroundingMineCountSum(board)).isEqualTo(0) },
            { assertThat(`p(0,1)`.findSurroundingMineCountSum(board)).isEqualTo(2) },
            { assertThat(`p(1,1)`.findSurroundingMineCountSum(board)).isEqualTo(2) },
            { assertThat(`p(2,1)`.findSurroundingMineCountSum(board)).isEqualTo(1) },
            { assertThat(`p(0,2)`.findSurroundingMineCountSum(board)).isEqualTo(1) },
            { assertThat(`p(2,2)`.findSurroundingMineCountSum(board)).isEqualTo(1) }
        )
    }

    @Test
    fun `셀 오픈 기능이 정상 동작`() {
        // given when, then
        assertAll(
            "open cell test",
            { assertThat(ClosedNonMine(CellPosition.of(0, 0)).open()).isExactlyInstanceOf(OpenedNonMine::class.java) },
            { assertThat(ClosedMine(CellPosition.of(0, 0)).open()).isExactlyInstanceOf(OpenedMine::class.java) },
            { assertThat(OpenedNonMine(CellPosition.of(0, 0)).open()).isExactlyInstanceOf(OpenedNonMine::class.java) },
            { assertThat(OpenedMine(CellPosition.of(0, 0)).open()).isExactlyInstanceOf(OpenedMine::class.java) },
        )
    }

    @Test
    fun `닫힌 지뢰 셀은 열림 상태가 false`() {
        // given, when, then
        assertThat(ClosedMine(CellPosition.of(0, 0)).isOpened).isFalse
    }

    @Test
    fun `닫힌 지뢰 셀은 지뢰 상태가 true`() {
        // given, when, then
        assertThat(ClosedMine(CellPosition.of(0, 0)).isMine).isTrue
    }

    @Test
    fun `닫혀있고 지뢰가 아닌 셀은 열림 상태가 false`() {
        // given, when, then
        assertThat(ClosedNonMine(CellPosition.of(0, 0)).isOpened).isFalse
    }

    @Test
    fun `닫혀있고 지뢰가 아닌 셀은 지뢰 상태가 false`() {
        // given, when, then
        assertThat(ClosedNonMine(CellPosition.of(0, 0)).isMine).isFalse
    }

    @Test
    fun `열려있고 지뢰인 셀은 열림 상태가 true`() {
        // given, when, then
        assertThat(OpenedMine(CellPosition.of(0, 0)).isOpened).isTrue
    }

    @Test
    fun `열려있고 지뢰인 셀은 지뢰 상태가 true`() {
        // given, when, then
        assertThat(OpenedMine(CellPosition.of(0, 0)).isMine).isTrue
    }

    @Test
    fun `열려있고 지뢰가 아닌 셀은 열림 상태가 true`() {
        // given, when, then
        assertThat(OpenedNonMine(CellPosition.of(0, 0)).isOpened).isTrue
    }

    @Test
    fun `열려있고 지뢰가 아닌 셀은 지뢰 상태가 false`() {
        assertThat(OpenedNonMine(CellPosition.of(0, 0)).isMine).isFalse
    }
}
