package com.mindsup.shrinkl.app.infrastructure.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import java.time.ZonedDateTime

@ReadingConverter
class StringToZonedDateTimeConverter : Converter<String, ZonedDateTime> {
  //DateToLocalDateTimeConverter
  override fun convert(date: String): ZonedDateTime = ZonedDateTime.parse(date)

}
