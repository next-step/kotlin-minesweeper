package com.nextstep.jngcii.minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RowElementTest {
    @Test
    fun `단순 증가 확인`() {
        val element = RowElement(false)

        assertThat(element.risk).isEqualTo(0)

        List(5) {
            element.increaseRisk()
            assertThat(element.risk).isEqualTo(it + 1)
        }
    }

    @Test
    fun `지뢰는 증가 불가 확인`() {
        assertThrows<IllegalStateException>("지뢰는 risk를 증가시킬 수 없습니다.") {
            RowElement(true).increaseRisk()
        }
    }
}
