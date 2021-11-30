package br.com.daluz.java.marveldcheroesapi.config;

import br.com.daluz.java.marveldcheroesapi.constans.DynamoDbConstants;
import br.com.daluz.java.marveldcheroesapi.constans.HeroesConstants;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import java.io.IOException;

public class HeroesData {
    public static void main(String[] args) throws IOException {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        HeroesConstants.HEROES_ENDPOINT_LOCAL,
                        HeroesConstants.REGION_DYNAMO_DB))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable(DynamoDbConstants.DYNAMO_TABLE_NAME);

        Item hero = new Item()
                .withPrimaryKey(DynamoDbConstants.DYNAMO_TABLE_COLUMNS_ID, 1)
                .withString(DynamoDbConstants.DYNAMO_TABLE_COLUMNS_NAME, "Mulher Maravilha")
                .withString(DynamoDbConstants.DYNAMO_TABLE_COLUMNS_UNIVERSE, "DC comics")
                .withNumber(DynamoDbConstants.DYNAMO_TABLE_COLUMNS_FILMS, 3);

        PutItemOutcome outcome = table.putItem(hero);
    }
}
