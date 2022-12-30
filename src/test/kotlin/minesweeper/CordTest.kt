package minesweeper

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

/**
 * @see MapCord
 */
class CordTest : ExpectSpec({

    expect("CordBuilder를 통해 Cord를 만들 수 있다.") {

        val cord = MapCordBuilder().setX(1).setY(3).build()

        cord.x shouldBe 1
        cord.y shouldBe 3
    }
})
