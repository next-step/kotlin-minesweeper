package domain

@JvmInline
value class MineCount(val value: Int) {
    init {
        require(value > 0) { "지뢰의 개수는 0개일 수 없습니다." }
    }

    companion object {
        fun from(row: Row, column: Column, count: Int): MineCount {
            require(row.times(column) > count) { "지뢰찾기 전체 칸수보다 지뢰개수가 클 수 없습니다." }
            return MineCount(count)
        }
    }
}
