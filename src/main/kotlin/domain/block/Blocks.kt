package domain.block

data class Blocks(
    val values: List<Block>
) {
    fun isMineIn(position: Position): Boolean {
        return getIn(position).isMine()
    }

    fun getIn(position: Position): Block {
        return values.find { it.isAt(position) }
            ?: throw IllegalArgumentException("${position}에 해당하는 칸을 찾을 수 없습니다.")
    }
}
