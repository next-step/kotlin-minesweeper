package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SafeZoneTest : StringSpec({
    "해당 땅이 안전한 땅인지 확인한다." {
        // given
        val zone = SafeZone()

        // when
        val actual = zone.isMine()

        // then
        actual shouldBe false
    }
})
