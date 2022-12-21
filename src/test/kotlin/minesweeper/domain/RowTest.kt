package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class RowTest {
    @Test
    fun `크기가 주어지면 그에 맞는 필드리스트를 생성한다`() {
        val row = Row.init(2)

        row.fields.size shouldBe 2
    }

    @Test
    fun `x 값을 통해 값을 구할 수 있다`() {
        val row = Row.init(2)

        row[0] shouldBe row.fields[0]
    }

    @Test
    fun `x 값을 통해 값을 설정할 수 있다`() {
        val row = Row.init(2)
        val mine = Mine()

        row[0] = mine

        row[0] shouldBe mine
        row.fields[0] shouldBe mine
    }
}
