package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class MineBoardGeneratorTest : FunSpec({
    test("지뢰 위치 정보에 맞는 지뢰판을 생성할 수 있다.") {
        // given
        val width = 5
        val height = 5
        val boardSize = BoardSize(width, height)
        val mineCount = 3
        val mineLocations = MineLocations(Point(1, 1), Point(1, 2))
        val boardInfoGenerator = BoardInfoGenerator(
            boardSize,
            mineCount,
            FixedMineLocationStrategy(mineLocations)
        )

        // when
        val actual = boardInfoGenerator.generate()

        // then
        mineLocations.points.forAll {
            actual.values[it.y][it.x] shouldBe MINE
        }
    }

    test("정해진 높이와 너비 내의 지뢰판을 생성할 수 있다.") {
        // given
        val width = 5
        val height = 5
        val boardSize = BoardSize(width, height)
        val mineCount = 3
        val boardInfoGenerator = BoardInfoGenerator(
            boardSize,
            mineCount,
        )

        // when
        val actual = boardInfoGenerator.generate()

        // then
        actual.values.size shouldBe width
        actual.values.forAll {
            it.size shouldBe height
        }
    }
})
