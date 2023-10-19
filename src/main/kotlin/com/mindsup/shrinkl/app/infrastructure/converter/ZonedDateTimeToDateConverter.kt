package com.mindsup.shrinkl.app.infrastructure.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

@WritingConverter
class ZonedDateTimeToDateConverter : Converter<ZonedDateTime, Date> {
  //DateToLocalDateTimeConverter
  override fun convert(zonedDateTime: ZonedDateTime): Date {
    return Date.from(zonedDateTime.toInstant());
  }

}
