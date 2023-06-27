package com.kshitizbali.weathy.domain.location

import android.location.Location

/**
 * Interface to get the current location of the user.
 */
interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}