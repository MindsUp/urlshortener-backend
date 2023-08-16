package com.mindsup.shrinkl.app.shortenedurl.dataprovider

import com.mindsup.shrinkl.app.shortenedurl.dataprovider.entity.ShortenedUrlEntity
import org.springframework.data.repository.CrudRepository

interface ShortenedUrlJpa: CrudRepository<ShortenedUrlEntity, String>
