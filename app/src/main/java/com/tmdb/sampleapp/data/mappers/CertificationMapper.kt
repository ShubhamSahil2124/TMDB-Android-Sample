package com.tmdb.sampleapp.data.mappers

import com.tmdb.sampleapp.data.model.certification.ReleaseDatesResponse
import com.tmdb.sampleapp.domain.model.Certification

class CertificationMapper {
    fun map(certificationList: List<ReleaseDatesResponse>?): List<Certification>{
        val certifications = mutableListOf<Certification>()
        certificationList?.let {
            certificationList.forEach {
                val certification = Certification(
                    certification = it.certification,
                    type = it.type
                )
                certifications.add(certification)
            }
        }
        return certifications
    }
}