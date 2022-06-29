package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineMapTest : StringSpec({

    "지뢰 맵은 입력받은 높이(height), 너비(weight) 만큼 칸을 만든다." {
        val mineMap = MineMap(10, 5)
        mineMap.map().size shouldBe 10
        mineMap.map().all { it.size == 5 } shouldBe true
    }

    "지뢰 맵의 높이, 너비는 0보다 커야 한다." {
        shouldThrowWithMessage<IllegalArgumentException>("Invalid Mine Map Size") {
            MineMap(-1, 0)
        }

        shouldThrowWithMessage<IllegalArgumentException>("Invalid Mine Map Size") {
            MineMap(0, 0)
        }

        shouldThrowWithMessage<IllegalArgumentException>("Invalid Mine Map Size") {
            MineMap(10, 0)
        }

        shouldThrowWithMessage<IllegalArgumentException>("Invalid Mine Map Size") {
            MineMap(0, 10)
        }
    }

    "입력받은 지뢰 수(mineCount) 만큼 지뢰를 배치한다." {
        val mineMap = MineMap(10, 5, 6)
        mineMap.map().flatten().count { it is MineCell } shouldBe 6
    }

    "입력받은 지뢰 수는 지뢰 맵 총 사이즈보다 클 수 없다." {
        shouldThrowWithMessage<IllegalArgumentException>("Invalid Mine Count") {
            MineMap(5, 5, 26)
        }
    }

    "입력받은 지뢰 수는 마이너스가 될 수 없다." {
        shouldThrowWithMessage<IllegalArgumentException>("Invalid Mine Count") {
            MineMap(5, 5, -3)
        }
    }

    "입력 받은 위치의 cell 주변에 지뢰가 있다면, 반환되는 주변 cell position이 없다" {
        mineMap {
            rows(NumberCell(1), NumberCell(1))
            rows(NumberCell(1), MineCell)
        }.safeAroundPositions(Pair(0, 0)) shouldBe emptyList()
    }

    "입력 받은 위치의 cell 이 안전할 때, 주변 cell position 을 반환한다" {
        mineMap {
            rows(NumberCell(0), NumberCell(0))
            rows(NumberCell(0), NumberCell(0))
        }.safeAroundPositions(Pair(0, 0)) shouldBe listOf(
            Pair(0, 1),
            Pair(1, 0),
            Pair(1, 1)
        )

        mineMap {
            rows(NumberCell(0), NumberCell(0), NumberCell(0))
            rows(NumberCell(0), NumberCell(0), NumberCell(0))
            rows(NumberCell(0), NumberCell(0), NumberCell(0))
        }.safeAroundPositions(Pair(1, 1)) shouldBe listOf(
            Pair(0, 0),
            Pair(0, 1),
            Pair(0, 2),
            Pair(1, 0),
            Pair(1, 2),
            Pair(2, 0),
            Pair(2, 1),
            Pair(2, 2)
        )
    }
})
