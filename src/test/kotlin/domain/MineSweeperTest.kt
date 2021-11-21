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
        MineLayer(fixture.board, fixture.selector)
            .layMines(Fixture.mineNumber, Fixture.position)
        mineSweeper = MineSweeper(fixture.board, fixture.selector)
    }

    @DisplayName("MineSweeper 를 통해, board 의 지뢰 제거를 시도할 수 있다.")
    @Test
    fun layMines() {
        mineSweeper.sweepMine(Fixture.position)
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
    fun successLayMines() {
        assertThat(mineSweeper.sweepMine(Fixture.position)).isTrue
    }

    @DisplayName("board 의 지뢰 제거를 실패하면, 지뢰찾기가 종료된다.")
    @Test
    fun failLayMines() {
        assertAll(Fixture.minePositions.map { { assertThat(mineSweeper.sweepMine(it)).isFalse } })
    }
}
