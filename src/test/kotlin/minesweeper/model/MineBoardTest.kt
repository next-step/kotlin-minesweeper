package minesweeper.model

import minesweeper.dto.MineBoardCreateDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("지뢰판 테스트")
class MineBoardTest {

    @Test
    fun `지뢰판이 정상적으로 생성`() {
        // given
        val boardCreateDto = MineBoardCreateDto(width = 5, height = 4, mineCount = 3)

        // when
        val mineBoard = MineBoard.of(boardCreateDto).board

        // then
        mineBoard.forEach { assertThat(it.size).isEqualTo(5) }
        assertThat(mineBoard.size).isEqualTo(4)
        assertThat(mineBoard.sumOf { it.mineCount }).isEqualTo(3)
    }
}
