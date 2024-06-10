package com.example.mvcJPA.database

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
        "com.example.mvcJPA"
    ],
    entityManagerFactoryRef = "mvcJPAEntityManager",
    transactionManagerRef = "mvcJPATransactionManager"
)
class WebfluxCoroutineJPADatabaseConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "mvc-jpa.master.datasource")
    fun mvcJPAMasterDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "mvc-jpa.master.datasource.hikari")
    fun mvcJPAMasterHikariDataSource(
        @Qualifier("mvcJPAMasterDataSourceProperties") masterProperty: DataSourceProperties,
    ): HikariDataSource {
        return masterProperty
            .initializeDataSourceBuilder()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Bean
    fun mvcJPANamedParameterJdbcTemplate(
        @Qualifier("mvcJPAMasterHikariDataSource") dataSource: DataSource,
    ): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "mvc-jpa.jpa")
    fun mvcJPAJpaProperties(): JpaProperties {
        return JpaProperties()
    }

    @Bean
    @Primary
    fun mvcJPAEntityManager(
        entityManagerFactoryBuilder: EntityManagerFactoryBuilder,
        configurableListableBeanFactory: ConfigurableListableBeanFactory,
        @Qualifier("mvcJPAMasterHikariDataSource") mvcJPADataSource: DataSource,
    ): LocalContainerEntityManagerFactoryBean {
        return entityManagerFactoryBuilder
            .dataSource(mvcJPADataSource)
            .packages(
                "com.example.mvcJPA"
            )
            .properties(
                mapOf(AvailableSettings.BEAN_CONTAINER to SpringBeanContainer(configurableListableBeanFactory))
            )
            .build()
    }

    @Bean
    @Primary
    fun mvcJPATransactionManager(
        @Qualifier("mvcJPAEntityManager") mvcJPAEntityManager: EntityManagerFactory,
    ): PlatformTransactionManager {
        return JpaTransactionManager(mvcJPAEntityManager)
    }

    @Bean
    fun persistenceExceptionTranslationPostProcessor(): PersistenceExceptionTranslationPostProcessor {
        return PersistenceExceptionTranslationPostProcessor()
    }
}