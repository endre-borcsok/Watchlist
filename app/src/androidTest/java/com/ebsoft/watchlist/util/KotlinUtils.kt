package com.ebsoft.watchlist.util

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

class KotlinUtils {
    companion object {
        @JvmStatic
        fun <T> mockDeferred(response: T): Deferred<T> {
            return CompletableDeferred(response)
        }
    }
}