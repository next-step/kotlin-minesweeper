package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineMapTest : StringSpec({
    "MineMap은 높이와 너비를 가진다" {
        val mineMap = MineMap(10, 10)
        mineMap.height shouldBe 10
        mineMap.width shouldBe 10
    }

    "MineMap은 지뢰가 있는 지점이 있고, 없는 지점도 있다" {
        val height = 10
        val width = 10
        var hasMine = false
        var hasNotMine = false
        val mineMap = MineMap(height, width)
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
})
