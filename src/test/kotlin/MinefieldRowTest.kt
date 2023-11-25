import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MinefieldRowTest {

    @Test
    fun `특정 위치의 값을 1 증가하고 새 객체를 반환한다`() {
        // given
        val minefieldRow = MinefieldRow.of(3)

        // when
        val result = minefieldRow.incrementMineCountsIfAdjacent(1)

        // then
        result.mineFields[1] shouldBe 1
    }

    @Test
    fun `특정 위치의 값을 -1 로 변경하고 반환한다`() {
        // given
        val minefieldRow = MinefieldRow.of(3)

        // when
        val result = minefieldRow.markAsMine(1)

        // then
        result.mineFields[1] shouldBe -1
    }
}
