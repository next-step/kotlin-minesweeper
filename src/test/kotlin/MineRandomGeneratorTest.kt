import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MineRandomGeneratorTest {
    @Test
    fun `랜덤으로 위치를 생성한다`() {
        // given
        val height = 2
        val width = 2
        val count = 1

        // given, when
        val pointList = MineRandomGenerator().generate(height, width, count)

        // when
        pointList.size shouldBe 1
    }

    @Test
    fun `랜덤으로 생성된 위치는 범위 내에 존재한다`() {
        // given
        val height = 2
        val width = 2
        val count = 1

        // given, when
        val pointList = MineRandomGenerator().generate(height, width, count)

        // when
        pointList[0].x shouldBeLessThan height
        pointList[0].y shouldBeLessThan width
    }
}
