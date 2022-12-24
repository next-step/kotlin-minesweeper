package com.nextstep.minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MineDispenseHelperTest : FunSpec({
    context("MineDispenseHelper") {
        test("지뢰 매설 위치 계산") {
            val total = Size(30)
            val width = Size(10)
            val mineCounts = 10

            val calculated = MineDispenseHelper.calculatePositions(total, mineCounts, width) { x -> x.toList() }

            calculated.size shouldBe 10
        }
    }
})
