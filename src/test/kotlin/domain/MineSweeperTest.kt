package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class MineSweeperTest {
    lateinit var fixture: Fixture

    @BeforeEach
    fun setUp() {
        fixture = Fixture()
        MineLayer(fixture.board, fixture.selector)
            .layMines(Fixture.mineNumber, Fixture.position)
    }

    @DisplayName("MineSweeper 를 통해, board 의 지뢰 제거를 시도할 수 있다.")
    @Test
    fun layMines() {
        val mineSweeper = MineSweeper(fixture.board, fixture.selector)
        mineSweeper.sweepMine(Fixture.position)
        assertThat(fixture.renderedBoard()).isEqualTo(
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

    @DisplayName("MineSweeper 를 통해, board 의 지뢰 제거를 성공하면, true 를 반환한다.")
    @Test
    fun successLayMines() {
        val mineSweeper = MineSweeper(fixture.board, fixture.selector)
        assertThat(mineSweeper.sweepMine(Fixture.position)).isTrue
    }

    @DisplayName("MineSweeper 를 통해, board 의 지뢰 제거를 실패하면, false 를 반환한다.")
    @Test
    fun failLayMines() {
        val mineSweeper = MineSweeper(fixture.board, fixture.selector)
        assertAll(Fixture.minePositions.map { { assertThat(mineSweeper.sweepMine(it)).isFalse } })
    }
}
