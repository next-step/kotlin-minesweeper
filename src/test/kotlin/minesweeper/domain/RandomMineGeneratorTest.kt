package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class RandomMineGeneratorTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 10, 13, 30, 42])
    fun `랜덤한 지뢰를 생성할 수 있다`(number: Int) {
        // given
        val width = 10
        val height = 10

        // when
        val mine = RandomMineGenerator().generate(mineCount = number, width = width, height = height)

        // then
        assertThat(mine.size).isEqualTo(number)
    }

    @Test
    fun `지뢰 개수가 1개 미만으로 입력시 에러가 발생한다`() {
        val width = 10
        val height = 10
        val mineCount = 0

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            RandomMineGenerator().generate(mineCount, width, height)
        }
            .withMessage("지뢰 개수는 1개 이상이어야 합니다.")
    }

    @Test
    fun `지뢰의 개수가 너비 * 높이 보다 많으면 에러가 발생한다`() {
        val width = 10
        val height = 10
        val mineCount = 101

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            RandomMineGenerator().generate(mineCount = mineCount, width = width, height = height)
        }
            .withMessage("지뢰 개수는 너비 * 높이 보다 작거나 같아야 합니다.")
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 48, 50, 77, 100])
    fun `지뢰 좌표는 맵 너비, 높이 값을 초과하면 안된다`(number: Int) {
        // given
        val width = 10
        val height = 10

        // when
        val lastBlock =
            RandomMineGenerator().generate(mineCount = number, width = width, height = height)
                .sortedWith(compareBy({ it.x }, { it.y }))
                .last()
        // then
        assertThat(((lastBlock.x + 1) * (lastBlock.y * 1)) <= (width * height)).isTrue
    }
}
