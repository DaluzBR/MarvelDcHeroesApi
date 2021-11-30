package br.com.daluz.java.marveldcheroesapi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

@Configuration
@EnableDynamoDBRepositories // Para reconhecer a configuração do DynamoDB.
public class DynamoConfig {
    @Value("${amazon.dynamodb.endpoint}") // Para passar o valor da chave.
    private String amazonDynamoDbEndpoint;

    @Value("${aws_access_key_id}") // Para fazer um placeholder deste valor
    private String amazonAwsAccessKey;

    @Value("${aws_secret_access_key}")
    private String amazonAWSSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAwsCredentials());
        if (!ObjectUtils.isEmpty(amazonDynamoDbEndpoint)) {
            amazonDynamoDB.setEndpoint(amazonDynamoDbEndpoint);
        }

        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAwsCredentials() {
        return new BasicAWSCredentials(
                amazonAwsAccessKey,
                amazonAWSSecretKey
        );
    }
}
