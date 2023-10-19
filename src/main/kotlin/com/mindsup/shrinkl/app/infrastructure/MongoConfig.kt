package com.mindsup.shrinkl.app.infrastructure

import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.mongodb.connection.ClusterSettings
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext
import org.bson.codecs.configuration.CodecRegistries
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*


@Configuration
class MongoConfig (val properties:MongoProperties) : AbstractMongoClientConfiguration() {

  override fun getDatabaseName(): String {
    return this.properties.database
  }

  override fun configureClientSettings(builder: MongoClientSettings.Builder) {
    builder
      .codecRegistry(CodecRegistries
        .fromRegistries(
          MongoClientSettings.getDefaultCodecRegistry(),
          CodecRegistries.fromCodecs(ZonedDateTimeCodec())
      ))
      .credential(MongoCredential
        .createCredential(
          this.properties.username,
          this.properties.database,
          this.properties.password))
      .applyToClusterSettings { settings: ClusterSettings.Builder ->
        settings.hosts(
          listOf(ServerAddress(this.properties.host, this.properties.port))
        )
      }
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
