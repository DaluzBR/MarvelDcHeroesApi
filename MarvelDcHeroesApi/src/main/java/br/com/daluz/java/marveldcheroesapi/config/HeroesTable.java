package br.com.daluz.java.marveldcheroesapi.config;

import br.com.daluz.java.marveldcheroesapi.constans.DynamoDbConstants;
import br.com.daluz.java.marveldcheroesapi.constans.HeroesConstants;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Tabela de heroes.
 */
@Configuration
@EnableDynamoDBRepositories
public class HeroesTable {
    public static void main(String[] args) throws Exception {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        HeroesConstants.HEROES_ENDPOINT_LOCAL,
                        HeroesConstants.REGION_DYNAMO_DB))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        String tableName = DynamoDbConstants.DYNAMO_TABLE_NAME;

        try {
            Table table = dynamoDB.createTable(
                    tableName,
                    List.of(new KeySchemaElement(DynamoDbConstants.DYNAMO_TABLE_COLUMNS_ID, KeyType.HASH)),
                    List.of(new AttributeDefinition(DynamoDbConstants.DYNAMO_TABLE_COLUMNS_ID, ScalarAttributeType.S)),
                    new ProvisionedThroughput(5L, 5L)
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
