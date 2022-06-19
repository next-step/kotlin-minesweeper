package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import vo.Point

internal class CellGroupTest : StringSpec({

    fun coordinate(x: Int, y: Int) = Coordinate(Point(x), Point(y))

    "빈 리스트로는 인스턴스 생성할 수 없다" {
        shouldThrow<IllegalArgumentException> {
            CellGroup(emptyList())
        }
    }

    "동일한 좌표를 가진 요소가 있으면 인스턴스 생성할 수 없다" {
        shouldThrow<IllegalArgumentException> {
            val cells = listOf(
                Mine(coordinate(1, 1)),
                Empty(coordinate(1, 1))
            )
            CellGroup(cells)
        }
    }

    "주어진 cell 을 가지는 인스턴스가 생성된다" {
        val cells = listOf(
            Mine(coordinate(1, 1)),
            Empty(coordinate(1, 2))
        )

        val cellGroup = CellGroup(cells)
        cellGroup.size shouldBe 2
    }
})
