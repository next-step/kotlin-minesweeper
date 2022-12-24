package com.nextstep.minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MineRowTest : FunSpec({
    context("MineRow") {
        test("minerow 사이즈") {
            val mineRow = MineRow(3)

            mineRow.size() shouldBe 3
        }

        test("minerow 매설 여부 확인 - 매설된 경우") {
            val mineRow = MineRow(3)
            mineRow.dispense(1)

            mineRow.isMined() shouldBe true
        }

        test("minerow 매설 여부 확인 - 매설안된 경우") {
            val mineRow = MineRow(3)

            mineRow.isMined() shouldBe false
        }
    }
})
