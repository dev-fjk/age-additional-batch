package com.batch.additional.age;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AgeAdditionalBatchApplication {

    public static void main(String[] args) {
        // Batchのため実行後はプログラムを終了させる。 正常終了時は終了コード 0
        System.exit(SpringApplication.exit(SpringApplication.run(AgeAdditionalBatchApplication.class, args)));
    }
}
