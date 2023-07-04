package model.minemark

sealed interface MineMark {
    val openStatus: OpenStatus
    val isOpened: Boolean
        get() = openStatus == OpenStatus.OPENED
    val opened: MineMark

    fun next(count: Int): MineMark
}

data class Safety(override val openStatus: OpenStatus = OpenStatus.CLOSED) : MineMark {
    override val opened: MineMark
        get() = Safety(OpenStatus.OPENED)

    override fun next(count: Int): MineMark {
        return MineCount(count)
    }
}

data class Mine(override val openStatus: OpenStatus = OpenStatus.CLOSED) : MineMark {
    override val opened: MineMark
        get() = Mine(OpenStatus.OPENED)

    override fun next(count: Int): MineMark {
        throw IllegalStateException("mine can not be next mark")
    }
}

data class MineCount(val count: Int, override val openStatus: OpenStatus = OpenStatus.CLOSED) : MineMark {
    override val opened: MineMark
        get() = MineCount(count, OpenStatus.OPENED)

    init {
        require(count >= 0) { "count must be greater than or equal to 0. but provided `$count`" }
    }

    override fun next(count: Int): MineMark {
        throw IllegalStateException("mine count can not be next mark")
    }
}
