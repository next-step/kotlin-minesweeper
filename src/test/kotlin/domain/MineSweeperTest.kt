package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class MineSweeperTest {
    lateinit var fixture: Fixture
    lateinit var mineSweeper: MineSweeper

    @BeforeEach
    fun setUp() {
        fixture = Fixture()
        val (board, selector, mineNumber) = fixture
        MineLayer(board, selector).layMines(mineNumber)
        mineSweeper = MineSweeper(board, selector)
        mineSweeper.sweepMine()
    }

    @DisplayName("MineSweeper 를 통해, board 의 지뢰 제거를 시도할 수 있다.")
    @Test
    fun sweepMine() {
        assertThat(fixture.renderBoard()).isEqualTo(
            """
                0 1 C C C C C C C C
                0 1 C C C C C C C C
                0 1 C C C C C C C C
                1 1 C C C C C C C C
                C C C C C C C C C C
                C C C C C C C C C C
                C C C C C C C C C C
                C C C C C C C C C C
                C C C C C C C C C C
                C C C C C C C C C C
            """.trimIndent()
        )
    }

    @DisplayName("board 의 지뢰 제거를 성공하면, 지뢰찾기를 계속할 수 있다.")
    @Test
    fun successSweepMine() {
        assertThat(mineSweeper.sweepMine()).isTrue
    }

    @DisplayName("board 의 지뢰 제거를 실패하면, 지뢰찾기가 종료된다.")
    @Test
    fun failSweepMine() {
        assertAll(
            Fixture.minePositions.map {
                { assertThat(mineSweeper.sweepMine(it)).isFalse }
            }
        )
    }

    @DisplayName("이미 지뢰가 제거된 위치에서 다시 지뢰를 제거해도, board 가 달라져서는 안된다.")
    @Test
    fun duplicatedSweepMine() {
        mineSweeper.sweepMine(Position(4, 2))
        assertThat(fixture.renderBoard()).isEqualTo(
            """
                0 1 C C C C C C C C
                0 1 C C C C C C C C
                0 1 C C C C C C C C
                1 1 C C C C C C C C
                C C C C C C C C C C
                C C C C C C C C C C
                C C C C C C C C C C
                C C C C C C C C C C
                C C C C C C C C C C
                C C C C C C C C C C
            """.trimIndent()
        )
    }
}
