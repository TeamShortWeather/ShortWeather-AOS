package org.shortweather.data.repository

import org.shortweather.data.model.Sample
import org.shortweather.domain.repository.SampleRepository
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor() : SampleRepository {
    override fun getSample(): Sample = Sample("test")
}