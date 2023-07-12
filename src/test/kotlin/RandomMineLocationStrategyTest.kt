import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.ints.shouldBeInRange

class RandomMineLocationStrategyTest : FunSpec({

    test("정해진 높이와 너비 내에서 랜덤으로 지뢰 위치를 지정할 수 있다.") {
        // given
        val strategy = RandomMineLocationStrategy()
        val width = 5
        val height = 5
        val boardSize = BoardSize(width, height)
        val mineCount = 3

        // when
        val actual = strategy.generateMineLocations(boardSize, mineCount)

        // then
        actual.points.forAll {
            it.x.shouldBeInRange(0 until width)
            it.y.shouldBeInRange(0 until height)
        }
    }
})
