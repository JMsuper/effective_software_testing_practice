plugins {
    id("java")
    id("jacoco")
}

group = "org.example"
version = "1.0-SNAPSHOT"

jacoco {
    toolVersion = "0.8.12"
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.8".toBigDecimal()
            }
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    implementation("org.apache.commons:commons-lang3:3.12.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // https://mvnrepository.com/artifact/org.jacoco/jacoco-maven-plugin
    implementation("org.jacoco:jacoco-maven-plugin:0.8.12")
    implementation("org.apache.maven.reporting:maven-reporting-api:3.1.1")

    // https://mvnrepository.com/artifact/net.jqwik/jqwik
    testImplementation("net.jqwik:jqwik:1.9.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.0")
}

tasks.test {
    useJUnitPlatform {
        includeEngines("jqwik")

        // Or include several Junit engines if you use them
        // includeEngines 'jqwik', 'junit-jupiter', 'junit-vintage'

        // includeTags 'fast', 'medium'
        // excludeTags 'slow'
    }

    include("**/*Properties.class")
    include("**/*Test.class")
    include("**/*Tests.class")
    finalizedBy(tasks.jacocoTestReport)
}