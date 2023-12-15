package mineswipper.domain.map

@JvmInline
value class Pedals(
    val value: List<Pedal>
) {
    fun get(index: Int): Pedal {
        val result = value.getOrNull(index)
        require(result != null) { ERROR_MESSAGE }
        return value[index]
    }

    companion object {
        private const val ERROR_MESSAGE: String = "값이 존재하지 않습니다."
    }
}
