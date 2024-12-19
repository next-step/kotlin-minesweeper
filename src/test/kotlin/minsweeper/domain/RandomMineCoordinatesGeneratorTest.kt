package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class RandomMineCoordinatesGeneratorTest {

    @Test
    fun `보드판 넓이 보다 지뢰갯수 크면 에러를 던져야 한다`() {
        // given
        val boardSize = BoardSize(10, 10)
        val mineCount = 101
        val generator = RandomMineCoordinatesGenerator()

        // when
        val result = assertThrows(IllegalArgumentException::class.java) {
            generator.generate(boardSize, mineCount)
        }

        // then
        assertThat(result.message).isEqualTo("지뢰 갯수가 높이 x 넓이를 초과할 수 없습니다")
    }

}
