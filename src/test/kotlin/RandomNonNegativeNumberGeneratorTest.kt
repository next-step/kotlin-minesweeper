import domain.RandomNonNegativeNumberGenerator
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RandomNonNegativeNumberGeneratorTest {
    @Test
    fun `주어진 개수만큼 지뢰 심어졌는지 확인`() {
        Assertions.assertThat(
            RandomNonNegativeNumberGenerator(to = 5, count = 2).generate()
        ).size().isEqualTo(2)
    }
}
