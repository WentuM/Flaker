package io.rotlabs.flakerandroidmonitor

import android.content.Context

interface FlakerMonitor {

    fun initialize(appContext: Context)

    fun captureException(throwable: Throwable, data: Map<String, Any?>? = null)
}
