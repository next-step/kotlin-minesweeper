package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class MineMapTest : ShouldSpec({

    should("1보다 작은 높이와 너비를 입력하면 에러가 발생한다") {
        shouldThrow<IllegalArgumentException> {
            MineMap(1, 0, 0)
        }
        shouldThrow<IllegalArgumentException> {
            MineMap(0, 1, 0)
        }
    }

    should("맵의 칸보다 더 많은 수의 지뢰를 입력하면 에러가 발생한다") {
        shouldThrow<IllegalArgumentException> {
            MineMap(2, 2, 5)
        }
    }

    context("10 X 10 map") {
        val height = 10
        val width = 10
        should("MineMap은 높이와 너비를 가진다") {
            val mineMap = MineMap(height, width, 10)
            mineMap.height shouldBe 10
            mineMap.width shouldBe 10
        }

        should("MineMap은 지뢰가 있는 지점이 있고, 없는 지점도 있다") {
            var hasMine = false
            var hasNotMine = false
            val mineMap = MineMap(height, width, 10)
            repeat(height) { y ->
                repeat(width) { x ->
                    if (mineMap.get(x, y).hasMine) {
                        hasMine = true
                    } else {
                        hasNotMine = true
                    }
                }
            }

            hasMine shouldBe true
            hasNotMine shouldBe true
        }

        should("MineMap에 지뢰 개수를 입력하면 입력한 수만큼의 지뢰가 있다") {
            val mineCount = 10
            val mineMap = MineMap(height, width, mineCount)
            var count = 0
            repeat(height) { y ->
                repeat(width) { x ->
                    if (mineMap.get(x, y).hasMine) {
                        count++
                    }
                }
            }

            count shouldBe mineCount
        }
    }
})
