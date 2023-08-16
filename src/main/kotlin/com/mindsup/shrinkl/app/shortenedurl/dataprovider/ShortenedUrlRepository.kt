package com.mindsup.shrinkl.app.shortenedurl.dataprovider

import com.mindsup.shrinkl.core.shortenedurl.dataprovider.ShortenedUrlDataProvider
import org.springframework.stereotype.Repository

@Repository
class ShortenedUrlRepository(
  val shortenedUrlJpa: ShortenedUrlJpa
): ShortenedUrlDataProvider {





}
