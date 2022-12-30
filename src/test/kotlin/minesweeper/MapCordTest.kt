package minesweeper

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.MapCord
import minesweeper.domain.MapCordBuilder

/**
 * @see MapCord
 */
class MapCordTest : ExpectSpec({

    expect("CordBuilder를 통해 Cord를 만들 수 있다.") {

        val cord = MapCordBuilder().setX(1).setY(3).build()

        cord.x shouldBe 1
        cord.y shouldBe 3
    }

    expect("MapCord는 음수 좌표 값을 가질 수 없다") {
        shouldThrowExactly<IllegalArgumentException> { MapCord(-1, 3) }
        shouldThrowExactly<IllegalArgumentException> { MapCord(1, -3) }
        shouldThrowExactly<IllegalArgumentException> { MapCord(-1, -3) }
    }
})
