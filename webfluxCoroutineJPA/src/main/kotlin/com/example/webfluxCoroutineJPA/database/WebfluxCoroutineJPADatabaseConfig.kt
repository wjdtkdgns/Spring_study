package com.example.webfluxCoroutineJPA.database

import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import org.hibernate.cfg.AvailableSettings
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.orm.hibernate5.SpringBeanContainer
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = [
        "com.example.webfluxCoroutineJPA"
    ],
    entityManagerFactoryRef = "webfluxCoroutineJPAEntityManager",
    transactionManagerRef = "webfluxCoroutineJPATransactionManager"
)
class WebfluxCoroutineJPADatabaseConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "webflux-coroutine-jpa.master.datasource")
    fun webfluxCoroutineJPAMasterDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "webflux-coroutine-jpa.master.datasource.hikari")
    fun webfluxCoroutineJPAMasterHikariDataSource(
        @Qualifier("webfluxCoroutineJPAMasterDataSourceProperties") masterProperty: DataSourceProperties,
    ): HikariDataSource {
        return masterProperty
            .initializeDataSourceBuilder()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Bean
    fun webfluxCoroutineJPANamedParameterJdbcTemplate(
        @Qualifier("webfluxCoroutineJPAMasterHikariDataSource") dataSource: DataSource,
    ): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "webflux-coroutine-jpa.jpa")
    fun webfluxCoroutineJPAJpaProperties(): JpaProperties {
        return JpaProperties()
    }

    @Bean
    @Primary
    fun webfluxCoroutineJPAEntityManager(
        entityManagerFactoryBuilder: EntityManagerFactoryBuilder,
        configurableListableBeanFactory: ConfigurableListableBeanFactory,
        @Qualifier("webfluxCoroutineJPAMasterHikariDataSource") webfluxCoroutineJPADataSource: DataSource,
    ): LocalContainerEntityManagerFactoryBean {
        return entityManagerFactoryBuilder
            .dataSource(webfluxCoroutineJPADataSource)
            .packages(
                "com.example.webfluxCoroutineJPA"
            )
            .properties(
                mapOf(AvailableSettings.BEAN_CONTAINER to SpringBeanContainer(configurableListableBeanFactory))
            )
            .build()
    }

    @Bean
    @Primary
    fun webfluxCoroutineJPATransactionManager(
        @Qualifier("webfluxCoroutineJPAEntityManager") webfluxCoroutineJPAEntityManager: EntityManagerFactory,
    ): PlatformTransactionManager {
        return JpaTransactionManager(webfluxCoroutineJPAEntityManager)
    }

    @Bean
    fun persistenceExceptionTranslationPostProcessor(): PersistenceExceptionTranslationPostProcessor {
        return PersistenceExceptionTranslationPostProcessor()
    }
}