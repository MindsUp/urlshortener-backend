package com.mindsup.shrinkl.app.infrastructure

import com.mindsup.shrinkl.app.infrastructure.converter.StringToZonedDateTimeConverter
import com.mindsup.shrinkl.app.infrastructure.converter.ZonedDateTimeToStringConverter
import com.mongodb.*
import com.mongodb.client.gridfs.codecs.GridFSFileCodecProvider
import com.mongodb.client.model.geojson.codecs.GeoJsonCodecProvider
import com.mongodb.connection.ClusterSettings
import org.bson.BsonReader
import org.bson.BsonType
import org.bson.BsonWriter
import org.bson.codecs.*
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.jsr310.Jsr310CodecProvider
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime


@Configuration
class MongoConfig (val properties:MongoProperties) : AbstractMongoClientConfiguration() {

  override fun getDatabaseName(): String = this.properties.database

  override fun autoIndexCreation() = true

  override fun configureClientSettings(builder: MongoClientSettings.Builder) {
    val replacement: Map<BsonType, Class<*>> = mapOf(BsonType.DATE_TIME to  ZonedDateTime::class.java)
    builder
      .codecRegistry(CodecRegistries
        .fromRegistries(
          CodecRegistries.fromProviders(
            listOf(
              ValueCodecProvider(),
              BsonValueCodecProvider(),
              DBRefCodecProvider(),
              DBObjectCodecProvider(),
              DocumentCodecProvider(BsonTypeClassMap(replacement), DocumentToDBRefTransformer()),
              CollectionCodecProvider(DocumentToDBRefTransformer()),
              IterableCodecProvider(DocumentToDBRefTransformer()),
              MapCodecProvider(DocumentToDBRefTransformer()),
              GeoJsonCodecProvider(),
              GridFSFileCodecProvider(),
              Jsr310CodecProvider(),
              JsonObjectCodecProvider(),
              BsonCodecProvider(),
              EnumCodecProvider(),
              Jep395RecordCodecProvider()
            )
          ),
          CodecRegistries.fromCodecs(ZonedDateTimeCodec()),
//          MongoClientSettings.getDefaultCodecRegistry()
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

  override fun customConversions(): MongoCustomConversions {
    return MongoCustomConversions(
        listOf(
          ZonedDateTimeToStringConverter(),
          StringToZonedDateTimeConverter()
      ))
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
