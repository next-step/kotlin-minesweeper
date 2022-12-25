package domain.pos

import java.lang.IllegalArgumentException

class RelativePos private constructor(
    override val value: Int
) : Pos {

    init {
        require(allowableRange.contains(value)) { "상대 좌표는 -1, 0, 1만 올 수 있어요." }
    }

    override fun plus(target: Pos): Pos = of(value + target.value)

    companion object {
        private const val MIN_VALUE = -1
        private const val MAX_VALUE = 1

        private val allowableRange = MIN_VALUE..MAX_VALUE
        private val cache: Map<Int, RelativePos> = allowableRange.associateWith { RelativePos(it) }

        fun of(value: Int): RelativePos {
            return cache[value] ?: throw IllegalArgumentException("${value}는 상대좌표로 올 수 없는 값이에요")
        }
    }
}
