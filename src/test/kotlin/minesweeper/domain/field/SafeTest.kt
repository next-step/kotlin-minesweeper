package minesweeper.domain.field

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class SafeTest{
    @Test
    fun `주변의 지뢰 개수를 알 수 있다`(){
        val safe = Safe(7)

        safe.aroundMineCount() shouldBe 7
    }

    @Test
    fun `Mine으로 설정할 수 없다`(){
        val safe = Safe(7)

        shouldThrow<IllegalStateException> { safe.mine()}
    }

    @Test
    fun `Safe로 설정할 수 없다`(){
        val safe = Safe(7)

        shouldThrow<IllegalStateException> { safe.safe(7)}
    }
}
