package br.com.daluz.java.marveldcheroesapi.document;

import br.com.daluz.java.marveldcheroesapi.constans.DynamoDbConstants;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = DynamoDbConstants.DYNAMO_TABLE_NAME)
public class Heroes {

    @Id
    @DynamoDBHashKey(attributeName = DynamoDbConstants.DYNAMO_TABLE_COLUMNS_ID)
    private String id;
    @DynamoDBHashKey(attributeName = DynamoDbConstants.DYNAMO_TABLE_COLUMNS_NAME)
    private String name;
    @DynamoDBHashKey(attributeName = DynamoDbConstants.DYNAMO_TABLE_COLUMNS_UNIVERSE)
    private String universe;
    @DynamoDBHashKey(attributeName = DynamoDbConstants.DYNAMO_TABLE_COLUMNS_FILMS)
    private int films;

}
