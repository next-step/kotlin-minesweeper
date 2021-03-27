package domain

import domain.coordinategenerator.BoardCoordinatesGenerator
import domain.coordinategenerator.FakeCoordinatesGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MineBoardFactoryTest {

    private val fakeCoordinatesGenerator = FakeCoordinatesGenerator()

    @Test
    fun `지뢰찾기판을 생성할 수 있다`() {
        val result = MineBoardFactory.create(BoardCoordinatesGenerator(1, 1), fakeCoordinatesGenerator)
        assertThat(result).isNotNull
    }
}
