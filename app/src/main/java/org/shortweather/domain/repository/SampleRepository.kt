package org.shortweather.domain.repository

import org.shortweather.data.model.Sample

interface SampleRepository {
    fun getSample(): Sample
}