package minesweeper.domain.position

import minesweeper.domain.board.BoardSize
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PositionsTest {

    @ParameterizedTest
    @CsvSource("1,1,1", "1,2,2", "2,2,4", "10,10,100")
    fun `포지션 크기가 맞는지 테스트`(inputWidth: Int, inputHeight: Int, inputPositionSize: Int) {
        // given
        val positions = Positions.of(BoardSize(inputWidth, inputHeight))

        // when
        val positionSize = positions.size

        // then
        assertThat(positionSize).isEqualTo(inputPositionSize)
    }

    @Test
    fun `카운트 수가 포지션 수보다 큰 경우 에러`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))

        // when
        val actual = runCatching { positions.createMinePosition(101) }.exceptionOrNull()

        // then
        assertThat(actual).hasMessageContaining("카운트 수가 전체 수보다 큽니다.")
    }
}
