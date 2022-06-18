package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("랜덤 지뢰판 생성기 테스트")
class RandomMineBoardGeneratorTest {

    @Test
    fun `너비가 1미만이면 예외 발생`() {
        // when, then
        val exception = assertThrows<IllegalArgumentException> { RandomMineBoardGenerator.generate(0, 2, 1) }
        assertThat(exception.message).isEqualTo("너비는 최소 1 이상이어야 합니다.")
    }

    @Test
    fun `높이가 1미만이면 예외 발생`() {
        // when, then
        val exception = assertThrows<IllegalArgumentException> { RandomMineBoardGenerator.generate(2, 0, 1) }
        assertThat(exception.message).isEqualTo("높이는 최소 1 이상이어야 합니다.")
    }

    @Test
    fun `지뢰 개수가 1개 미만이면 예외 발생`() {
        // when, then
        val exception = assertThrows<IllegalArgumentException> { RandomMineBoardGenerator.generate(2, 2, 0) }
        assertThat(exception.message).isEqualTo("지뢰 개수는 최소 1개 이상이어야 합니다.")
    }

    @Test
    fun `지뢰 개수가 지뢰판 사이즈를 초과하면 예외 발생`() {
        // when, then
        val exception = assertThrows<IllegalArgumentException> { RandomMineBoardGenerator.generate(2, 2, 5) }
        assertThat(exception.message).isEqualTo("지뢰 개수는 4개를 초과할 수 없습니다.")
    }

    @Test
    fun `랜덤 지뢰판이 정상적으로 생성`() {
        // given, when
        val mineBoard = RandomMineBoardGenerator.generate(5, 4, 3)

        // then
        mineBoard.forEach { assertThat(it.size).isEqualTo(5) }
        assertThat(mineBoard.size).isEqualTo(4)
        assertThat(mineBoard.sumOf { it.mineCount }).isEqualTo(3)
    }
}
