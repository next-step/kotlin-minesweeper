package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineZoneTest : StringSpec({
    "해당 땅이 지뢰 땅인지 확인한다." {
        // given
        val zone = MineZone()

        // when
        val actual = zone.isMine()

        // then
        actual shouldBe true
    }
})
