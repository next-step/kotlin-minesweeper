package minesweeper.domain.board

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

    @ParameterizedTest
    @CsvSource("1,1,1", "1,2,2", "2,2,2", "10,10,50")
    fun `포지션을 만들고 카운트를 통하여 랜덤 포지션을 뽑아온다`(inputWidth: Int, inputHeight: Int, inputCount: Int) {
        // given
        val positions = Positions.of(BoardSize(inputWidth, inputHeight))

        // when
        val randomPosition = positions.getRandomPositions(inputCount).size

        // then
        assertThat(randomPosition).isEqualTo(inputCount)
    }

    @Test
    fun `카운트 수가 포지션 수보다 큰 경우 에러`() {
        // given
        val positions = Positions.of(BoardSize(10, 10))

        // when
        val actual = runCatching { positions.getRandomPositions(1000) }.exceptionOrNull()

        // then
        assertThat(actual).hasMessageContaining("카운트 수가 전체 수보다 큽니다.")
    }
}
