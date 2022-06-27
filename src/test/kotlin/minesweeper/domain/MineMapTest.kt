package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineMapTest : StringSpec({

    "지뢰 맵은 입력받은 높이(height), 너비(weight) 만큼 칸을 만든다." {
        val mineMap = MineMap.build(10, 5)
        mineMap.map().size shouldBe 10
        mineMap.map().all { it.size == 5 } shouldBe true
    }

    "지뢰 맵의 높이, 너비는 0보다 커야 한다." {
        shouldThrowWithMessage<IllegalArgumentException>("Invalid Mine Map Size") {
            MineMap.build(-1, 0)
        }

        shouldThrowWithMessage<IllegalArgumentException>("Invalid Mine Map Size") {
            MineMap.build(0, 0)
        }

        shouldThrowWithMessage<IllegalArgumentException>("Invalid Mine Map Size") {
            MineMap.build(10, 0)
        }

        shouldThrowWithMessage<IllegalArgumentException>("Invalid Mine Map Size") {
            MineMap.build(0, 10)
        }
    }

    "입력받은 지뢰 수(mineCount) 만큼 지뢰를 랜덤하게 배치한다." {
        val mineMap = MineMap.build(10, 5, 6)
        mineMap.map().flatten().count { it } shouldBe 6
    }

    "입력받은 지뢰 수는 지뢰 맵 총 사이즈보다 클 수 없다." {
        shouldThrowWithMessage<IllegalArgumentException>("Invalid Mine Count") {
            MineMap.build(5, 5, 26)
        }
    }
})
