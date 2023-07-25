package minesweeper.domain.generator

import io.kotest.matchers.ints.shouldBeInRange

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.RepeatedTest

class RandomPositionGeneratorTest {

    @RepeatedTest(100)
    fun `랜덤 포지션 생성기는 0부터 파라미터 까지의 값을 갖는 포지션을 생성한다`() {
        val generator = RandomPositionGenerator()

        val position = generator.get(10, 10)

        position.x shouldBeInRange 0..9
        position.y shouldBeInRange 0..9
    }
}
