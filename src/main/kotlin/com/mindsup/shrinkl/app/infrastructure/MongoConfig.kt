package com.mindsup.shrinkl.app.infrastructure

import com.mongodb.MongoClientSettings
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext
import org.bson.codecs.configuration.CodecRegistries
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime

@Configuration
class MongoConfig : AbstractMongoClientConfiguration() {

  @Value("\${spring.data.mongodb.database}")
  lateinit var dbname: String

  override fun getDatabaseName(): String {
    return dbname
  }

  override fun mongoClientSettings(): MongoClientSettings {
    val codecRegistry = CodecRegistries.fromRegistries(
      MongoClientSettings.getDefaultCodecRegistry(),
      CodecRegistries.fromCodecs(ZonedDateTimeCodec())
    )

    return MongoClientSettings.builder()
      .codecRegistry(codecRegistry)
      .build()
  }

  class ZonedDateTimeCodec : Codec<ZonedDateTime> {
    override fun getEncoderClass(): Class<ZonedDateTime> {
      return ZonedDateTime::class.java
    }

    override fun encode(writer: BsonWriter, value: ZonedDateTime, encoderContext: EncoderContext) {
      writer.writeDateTime(value.toInstant().toEpochMilli())
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext): ZonedDateTime {
      val epochMillis = reader.readDateTime()
      return ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneOffset.UTC)
    }
  }
}
