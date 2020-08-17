package minesweeper.domain.cell

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MineGeneratorTest {
    @DisplayName(value = "생성할 Mine의 수와, 결과의 size는 같아야한다.")
    @Test
    fun happyCase() {
        val generator = MineGenerator(100)
        val mineSize = 10
        assertThat(generator.getRandomPosition(mineSize).size).isEqualTo(mineSize)
    }

    @DisplayName(value = "전체 크기보다, 생성할 지뢰가 많을수 없다.")
    @Test
    fun validationCheck() {
        val generator = MineGenerator(10)
        val mineSize = 11
        assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy {
                generator.getRandomPosition(mineSize)
            }
    }
}
