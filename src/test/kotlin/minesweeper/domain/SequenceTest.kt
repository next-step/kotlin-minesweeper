package minesweeper.domain

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class SequenceTest : ShouldSpec({
    should("값이 같은 두 Sequence는 동일 하다.") {
        Sequence(10) shouldBe Sequence(10)
    }

    should("Point로 부터 Sequence로 변환할 수 있다.") {
        val converter = SequenceConverter(3)

        listOf(
            Point(0, 0) to Sequence(0),
            Point(1, 0) to Sequence(1),
            Point(2, 0) to Sequence(2),
            Point(0, 1) to Sequence(3),
            Point(1, 1) to Sequence(4),
            Point(2, 1) to Sequence(5),
        ).forAll { (point, sequence) -> converter.sequence(point) shouldBe sequence }
    }

    should("Sequence로 부터 Point로 변환할 수 있다.") {
        val converter = SequenceConverter(3)

        listOf(
            Sequence(0) to Point(0, 0),
            Sequence(1) to Point(1, 0),
            Sequence(2) to Point(2, 0),
            Sequence(3) to Point(0, 1),
            Sequence(4) to Point(1, 1),
            Sequence(5) to Point(2, 1),
        ).forAll { (sequence, point) -> converter.point(sequence) shouldBe point }
    }
})
