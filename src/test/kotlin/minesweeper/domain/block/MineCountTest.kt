package minesweeper.domain.block

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("지뢰 개수(MineCount)")
internal class MineCountTest {

    @ParameterizedTest(name = "입력 값: {0}")
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8])
    fun `지뢰 개수는 0과 8사이의 숫자로 이루어진다`(mineCountInt: Int) {
        val mineCount = MineCount.from(mineCountInt)

        assertAll(
            { assertThat(mineCount).isNotNull },
            { assertThat(mineCount).isExactlyInstanceOf(MineCount::class.java) }
        )
    }
}
