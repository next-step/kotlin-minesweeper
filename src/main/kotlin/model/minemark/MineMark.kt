package model.minemark

sealed interface MineMark {
    fun next(count: Int): MineMark
}

object Safety : MineMark {
    override fun next(count: Int): MineMark {
        return MineCount(count)
    }
}

object Mine : MineMark {
    override fun next(count: Int): MineMark {
        throw IllegalStateException("mine can not be next mark")
    }
}

data class MineCount(val count: Int) : MineMark {
    init {
        require(count >= 0) { "count must be greater than or equal to 0. but provided `$count`" }
    }

    override fun next(count: Int): MineMark {
        throw IllegalStateException("mine count can not be next mark")
    }
}
