package minesweeper.domain.position

import minesweeper.domain.board.BoardSize
import org.assertj.core.api.Assertions.assertThat
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
}
