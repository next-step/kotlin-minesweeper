package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomPositionGeneratorTest {

    private fun sut(
        randomGenerator: RandomGenerator,
        xFrom: Int,
        xUntil: Int,
        yFrom: Int,
        yUntil: Int
    ): RandomPositionGenerator = RandomPositionGenerator(
        randomGenerator,
        xFrom,
        xUntil,
        yFrom,
        yUntil
    )

    @Test
    fun `x좌표의 허용범위와 y 좌표의 허용범위를 입력받아 임의의 count 만큼의 위치를 반환할 수 있다`() {
        // given
        val testRandomGenerator = TestRandomGenerator().apply {
            willReturns {
                integer(3)
                integer(5)
                integer(4)
                integer(6)
            }
        }

        val count = 2
        val xFrom = 0
        val xUntil = 5
        val yFrom = 2
        val yUntil = 10
        val sut = sut(testRandomGenerator, xFrom, xUntil, yFrom, yUntil)

        // when
        val positions = sut.generate(count)

        // then
        assertThat(positions.size).isEqualTo(2)
        assertThat(positions[0]).isEqualTo(Position(3, 5))
        assertThat(positions[1]).isEqualTo(Position(4, 6))
    }
}

private class TestRandomGenerator : RandomGenerator {
    private val nums = mutableListOf<Int>()

    override fun random(from: Int, until: Int): Int {
        return nums.removeFirst()
    }

    fun willReturns(block: CustomRandomFixture.() -> Unit) = object : CustomRandomFixture {
        override fun integer(num: Int) {
            nums.add(num)
        }
    }.block()
}

interface CustomRandomFixture {
    fun integer(num: Int)
}
