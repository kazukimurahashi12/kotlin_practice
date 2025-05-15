plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    id("nu.studer.jooq") version "8.2"
    id("application")
}

application {
    mainClass.set("org.example.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("com.mysql:mysql-connector-j")

    // JOOQ DSL
    jooqGenerator("com.mysql:mysql-connector-j")
}

jooq {
    version.set("3.18.2")
    edition.set(nu.studer.gradle.jooq.JooqEdition.OSS)

    configurations {
        create("main") {
            jooqConfiguration.apply {
                jdbc.apply {
                    driver = "com.mysql.cj.jdbc.Driver"
                    url = "jdbc:mysql://localhost:3306/local?serverTimezone=Asia/Tokyo"
                    user = "testuser"
                    password = "testpw"
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.mysql.MySQLDatabase"
                        inputSchema = "local"

                        // 小文字でマッチするように修正
                        forcedTypes.add(
                            org.jooq.meta.jaxb.ForcedType().apply {
                                name = "BOOLEAN"
                                includeExpression = ".*\\.is_active"  // 小文字に修正
                                includeTypes = "TINYINT\\(1\\)"  // TINYINT(1)に完全一致
                            }
                        )
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = true
                    }
                    target.apply {
                        packageName = "com.example.jooq.generated"
                        directory = "build/generated-sources/jooq"
                    }
                }
            }
        }
    }
}