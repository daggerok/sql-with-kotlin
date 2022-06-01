package daggerok.sqlwithkotlin

import org.apache.logging.log4j.kotlin.logger
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SqlWithKotlinApplicationTests(@Autowired val props: SpringDatasourceProperties) : AbstractTestcontainersTest() {

    @Test
    fun `should test database connection`() {
        // given
        log.info { "props: $props" }

        // and
        Database.connect(props.url, props.driver, props.user, props.password)

        // when
        transaction {
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(Messages)

            val newMessage = MessageEntity
                .new { body = "Hello, World!" }
                .toDTO()

            // then
            log.info { "newMessage: $newMessage" }

            // and
            assertThat(newMessage.id).isGreaterThanOrEqualTo(1)
        }
    }

    companion object {
        val log = logger()

        object Messages: LongIdTable() {
            val body = text("body")
        }

        class MessageEntity(id: EntityID<Long>) : LongEntity(id) {
            companion object : LongEntityClass<MessageEntity>(Messages) {
                data class Message(val id: Long, val body: String)
            }

            var body by Messages.body

            override fun toString(): String = toDTO().toString()

            fun toDTO(): Message = Message(id.value, body)
        }
    }
}
