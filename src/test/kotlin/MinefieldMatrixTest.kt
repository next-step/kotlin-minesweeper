import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MinefieldMatrixTest {

    @Test
    fun `해당 위치에 지뢰를 심고 주변 지뢰아닌 곳에 지뢰개수를 증가한다`() {
        // given
        val minefieldMatrix = MinefieldMatrix.of(3, 3)

        // when
        val result = minefieldMatrix.updateSurroundingMineCounts(1, 1)

        // then
        result.matrix shouldBe listOf(
            MinefieldRow(listOf(1, 1, 1)),
            MinefieldRow(listOf(1, -1, 1)),
            MinefieldRow(listOf(1, 1, 1))
        )
    }

    @Test
    fun `2차원 리스트로 반환한다`() {
        // given
        val minefieldMatrix = MinefieldMatrix.of(3, 3)

        // when
        val result = minefieldMatrix.getMap()

        // then
        result shouldBe listOf(
            listOf(0, 0, 0),
            listOf(0, 0, 0),
            listOf(0, 0, 0)
        )
    }
}
