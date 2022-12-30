package minesweeper

import io.kotest.assertions.throwables.shouldThrowExactly
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

    expect("X좌표나 Y좌표가 0인 경우에는 Cord를 만들 수 없다.") {
        shouldThrowExactly<IllegalArgumentException> {
            CordBuilder().setX(0).setY(-1).build()
        }
        shouldThrowExactly<IllegalArgumentException> {
            CordBuilder().setX(-1).setY(0).build()
        }
        shouldThrowExactly<IllegalArgumentException> {
            CordBuilder().setX(-1).setY(-1).build()
        }
    }
})
