package com.mindsup.shrinkl.app.infrastructure.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.Date

@ReadingConverter
class DateToZonedDateTimeConverter : Converter<Date, ZonedDateTime> {
  //DateToLocalDateTimeConverter
  override fun convert(date: Date): ZonedDateTime {
    return date.toInstant().atZone(ZoneOffset.UTC);
  }

}
