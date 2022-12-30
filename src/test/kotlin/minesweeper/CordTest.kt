package minesweeper

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

/**
 * @see Cord
 */
class CordTest : ExpectSpec({

    expect("CordBuilder를 통해 Cord를 만들 수 있다.") {

        val cord = CordBuilder().setX(0).setY(0).build()

        cord.x shouldBe 0
        cord.y shouldBe 0
    }
})
