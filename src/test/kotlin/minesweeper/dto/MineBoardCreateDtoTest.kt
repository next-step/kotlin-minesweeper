package minesweeper.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("지뢰판 생성 dto 테스트")
class MineBoardCreateDtoTest {

    @Test
    fun `dto 생성시 너비가 1 미만이면 예외 발생`() {
        // when, then
        val exception =
            assertThrows<IllegalArgumentException> { MineBoardCreateDto(width = 0, height = 2, mineCount = 1) }
        assertThat(exception.message).isEqualTo("너비는 최소 1 이상이어야 합니다.")
    }

    @Test
    fun `dto 생성시 높이가 1 미만이면 예외 발생`() {
        // when, then
        val exception =
            assertThrows<IllegalArgumentException> { MineBoardCreateDto(width = 2, height = 0, mineCount = 1) }
        assertThat(exception.message).isEqualTo("높이는 최소 1 이상이어야 합니다.")
    }

    @Test
    fun `dto 생성시 지뢰 개수가 1개 미만이면 예외 발생`() {
        // when, then
        val exception =
            assertThrows<IllegalArgumentException> { MineBoardCreateDto(width = 2, height = 2, mineCount = 0) }
        assertThat(exception.message).isEqualTo("지뢰 개수는 최소 1개 이상이어야 합니다.")
    }

    @Test
    fun `dto 생성시 지뢰 개수가 지뢰판 사이즈를 초과하면 예외 발생`() {
        // when, then
        val exception =
            assertThrows<IllegalArgumentException> { MineBoardCreateDto(width = 2, height = 2, mineCount = 5) }
        assertThat(exception.message).isEqualTo("지뢰 개수는 4개를 초과할 수 없습니다.")
    }
}
