package com.mindsup.shrinkl.app.infrastructure.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@WritingConverter
class ZonedDateTimeToStringConverter : Converter<ZonedDateTime, String> {
  //DateToLocalDateTimeConverter
  override fun convert(zonedDateTime: ZonedDateTime): String = zonedDateTime.format(DateTimeFormatter.ISO_DATE_TIME)

}
