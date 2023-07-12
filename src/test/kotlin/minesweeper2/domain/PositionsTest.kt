package minesweeper2.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper2.model.PositionLocation

class PositionsTest : StringSpec({
    "높이, 너비, 지뢰의 수를 통하여 지뢰의 Positions을 랜덤 생성한다." {
        val positions = Positions.from(Row(10), Col(10), Mine(10, 10, 10))

        positions.mineCount() shouldBe 10
        positions.position(0, 0).isOpened shouldBe false
        positions.position(0, 0).isVisited shouldBe false
    }

    "포지션 선택 시 지뢰가 없는 인접 칸이 모두 열리게 된다." {
        val positions = Positions(positionArrays())

        positions.open(PositionLocation(0, 0))
        positions.position(0, 0).isOpened shouldBe true
        positions.position(0, 1).isOpened shouldBe true
        positions.position(1, 0).isOpened shouldBe true
        positions.position(1, 1).isOpened shouldBe true
    }

    "열리지 않은 칸의 수를 알 수 있다." {
        val positions = Positions(positionArrays())

        positions.open(PositionLocation(0, 0))

        positions.notOpenPositionCount() shouldBe 96
    }
})

private fun positionArrays(): Array<Array<Position>> {
    val positionArrays: Array<Array<Position>> = Array(10) {
        Array(10) { Position() }
    }
    positionArrays[1][2] = Position(-1)
    positionArrays[2][1] = Position(-1)
    positionArrays[2][2] = Position(-1)
    return positionArrays
}
