package daggerok.sqlwithkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootApplication
internal class AbstractTestcontainersTestApp

@Testcontainers
@ActiveProfiles("integration-testing")
@SpringBootTest(webEnvironment = NONE, classes = [AbstractTestcontainersTestApp::class])
abstract class AbstractTestcontainersTest {

    companion object {
        data class TestMySQLContainer(val image: String = "mysql:8.0.24") : MySQLContainer<TestMySQLContainer>(image)

        @Container
        val mysqlContainer: TestMySQLContainer = TestMySQLContainer().withDatabaseName("database")

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.user", mysqlContainer::getUsername)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.driver", mysqlContainer::getDriverClassName)
        }
    }
}
