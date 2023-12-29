import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class RandomMinePositionsGeneratorTest {

    @Test
    fun `랜덤 지뢰 위치 생성`() {
        val generator = RandomMinePositionsGenerator(5, 5)

        val actual = generator.generate(5)

        assertThat(actual).hasSize(5)
        assertThat(actual)
            .hasSize(5)
            .allMatch { it.x in (0 until 5) }
            .allMatch { it.y in (0 until 5) }
    }

    @Test
    fun `생성할 수 있는 지뢰 위치 갯수 넘길 경우 예외 발`() {
        val generator = RandomMinePositionsGenerator(2, 2)

        assertThrows<IllegalArgumentException> {
            generator.generate(5)
        }
    }
}
