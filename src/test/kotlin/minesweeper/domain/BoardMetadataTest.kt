package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BoardMetadataTest {
    @ParameterizedTest
    @ValueSource(ints = [0, -1, -10])
    fun `보드 메타 데이터의 높이는 0보다 큰 양수만 허용한다`(input: Int) {
        shouldThrow<IllegalArgumentException> {
            BoardMetadata(input, 10, 10)
        }.message shouldBe "높이는 0보다 큰 양수만 허용합니다."
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1, -10])
    fun `보드 메타 데이터의 넓이는 0보다 큰 양수만 허용한다`(input: Int) {
        shouldThrow<IllegalArgumentException> {
            BoardMetadata(10, input, 10)
        }.message shouldBe "넓이는 0보다 큰 양수만 허용합니다."
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1, -10])
    fun `보드 메타 데이터의 지뢰의 개수는 0보다 큰 양수만 허용한다`(input: Int) {
        shouldThrow<IllegalArgumentException> {
            BoardMetadata(10, 10, input)
        }.message shouldBe "지뢰의 개수는 0보다 큰 양수만 허용합니다."
    }

    @Test
    fun `보드의 전체 크기보다 많은 개수의 지뢰는 가질 수 없습니다`() {
        shouldThrow<IllegalArgumentException> {
            BoardMetadata(10, 10, 101)
        }.message shouldBe "지뢰의 개수는 전체 칸의 개수보다 작아야 합니다."
    }
}
