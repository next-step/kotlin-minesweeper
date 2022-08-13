package v2minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MineSweeperBoardFactoryTest : FunSpec({
    test("총 zone 갯수(높이 x 너비)보다 지뢰 갯수가 더 많으면 예외를 발생시킨다.") {
        // given
        val invalidMineNumber = 10
        val height = 3
        val width = 3

        // when then
        shouldThrowExactly<IllegalArgumentException> {
            MineSweeperBoardFactory(
                height = Height(height),
                width = Width(width),
                mineNumber = MineNumber(invalidMineNumber)
            )
        }
    }

    test("지정한 높이, 너비, 지뢰 갯수에 맞게 지뢰판을 생성한다.") {
        // given
        val height = 3
        val width = 3
        val mineNumber = 4
        val mineSweeperBoardFactory = MineSweeperBoardFactory(
            height = Height(height),
            width = Width(width),
            mineNumber = MineNumber(mineNumber)
        )

        // when
        val mineSweeperBoard = mineSweeperBoardFactory.create()

        // then
        assertSoftly(mineSweeperBoard) {
            // TODO 2022-08-14 경록: 랜덤한 요소에 의존 중인 테스트 (개선 필요)
            zones.containsKey(1 to 1) shouldBe true
            zones.containsKey(1 to 2) shouldBe true
            zones.containsKey(1 to 3) shouldBe true
            zones.containsKey(2 to 1) shouldBe true
            zones.containsKey(2 to 2) shouldBe true
            zones.containsKey(2 to 3) shouldBe true
            zones.containsKey(3 to 1) shouldBe true
            zones.containsKey(3 to 2) shouldBe true
            zones.containsKey(3 to 3) shouldBe true
        }
    }
})
