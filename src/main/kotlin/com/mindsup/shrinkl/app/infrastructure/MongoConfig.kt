package com.mindsup.shrinkl.app.infrastructure

import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.UuidRepresentation
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext
import org.bson.codecs.configuration.CodecRegistries
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*



@Configuration
class MongoConfig : AbstractMongoClientConfiguration() {

//  @Value("\${spring.data.mongodb.database}")
//  lateinit var dbname: String
//
//  @Value("\${spring.data.mongodb.username}")
//  lateinit var user: String
//
//  @Value("\${spring.data.mongodb.password}")
//  lateinit var pwd: String

  override fun getDatabaseName(): String {
    return "urlshortener"
  }

//  public override fun configureClientSettings(builder: MongoClientSettings.Builder) {
//    val codecRegistry = CodecRegistries.fromRegistries(
//      MongoClientSettings.getDefaultCodecRegistry(),
//      CodecRegistries.fromCodecs(ZonedDateTimeCodec())
//    )
//    builder.codecRegistry(codecRegistry)
//  }

//  override fun co

//  override fun mongoClientSettings(): MongoClientSettings {
//    val codecRegistry = CodecRegistries.fromRegistries(
//      MongoClientSettings.getDefaultCodecRegistry(),
//      CodecRegistries.fromCodecs(ZonedDateTimeCodec())
//    )
//
//    return MongoClientSettings.builder()
//      .credential(MongoCredential.createCredential(user, dbname, pwd.toCharArray()))
//      .applyToClusterSettings { settings -> settings.hosts(listOf(ServerAddress("127.0.0.1", 27017))) }
//      .codecRegistry(codecRegistry)
//      .build()
//  }

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
