package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("랜덤 지뢰판 생성기 테스트")
class RandomMineFieldGeneratorTest {

    @Test
    fun `랜덤 지뢰판이 정상적으로 생성`() {
        // given, when
        val mineField = RandomMineFieldGenerator.generate(5, 4, 3)

        // then
        mineField.forEach { assertThat(it.size).isEqualTo(5) }
        assertThat(mineField.size).isEqualTo(4)
        assertThat(mineField.sumOf { it.mineSize }).isEqualTo(3)
    }
}
