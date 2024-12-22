package minsweeper.domain.generate

import minsweeper.domain.board.BoardSize
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RandomMineGeneratorTest {

    @Test
    fun `지뢰 갯수가 보드 크기의 넓이보다 크면 에러를 던져야 한다`() {
        // given
        val boardSize = BoardSize(10, 10)
        val mineAmount = 101
        val mineGenerator = RandomMineGenerator()

        // when
        val result = assertThrows<IllegalArgumentException> { mineGenerator.generate(boardSize, mineAmount) }

        // then
        assertThat(result.message).isEqualTo("지뢰 갯수는 판의 셀 갯수보다 클 수 없습니다")
    }

}