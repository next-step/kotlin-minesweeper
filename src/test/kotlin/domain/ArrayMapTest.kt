package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ArrayMapTest : StringSpec({
    val fixedMap = listOf(
        "0 0 0 0 1",
        "0 0 0 0 0",
        "0 0 0 0 0",
        "1 0 0 0 0"
    )

    "ArrayMap에서 get으로 지정 위치의 값을 가져올 수 있다" {
        val arrayMap = fixedMap.toArrayMap()
        arrayMap.get(Point(0, 0)).hasMine.shouldBe(false)
        arrayMap.get(Point(0, 4)).hasMine.shouldBe(true)
        arrayMap.get(Point(3, 0)).hasMine.shouldBe(true)
    }

    "ArrayMap에서 닫혀진 지점의 개수를 구한다" {
        val arrayMap = fixedMap.toArrayMap()
        arrayMap.get(Point(0, 0)).open()
        arrayMap.get(Point(0, 1)).open()
        arrayMap.get(Point(0, 2)).open()

        arrayMap.getClosedCount().shouldBe(17)
    }
})

fun List<String>.toArrayMap(): ArrayMap =
    ArrayMap(map { it.split(" ").map { mine -> Spot(mine == "1") } })
