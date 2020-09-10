package io.bloco.template.data.models

data class Counter(val value: Int) {
    operator fun inc(): Counter {
        return Counter(value + 1)
    }

    operator fun dec(): Counter {
        return Counter(value - 1)
    }

    override fun toString(): String {
        return value.toString()
    }
}