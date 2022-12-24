package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class RowTest {
    @Test
    fun `크기가 주어지면 그에 맞는 필드리스트를 생성한다`() {
        val row = Row.init(2)

        row.fields.size shouldBe 2
    }
}
