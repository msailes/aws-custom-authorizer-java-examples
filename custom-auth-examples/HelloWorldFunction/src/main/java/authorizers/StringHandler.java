package authorizers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2CustomAuthorizerEvent;

public class StringHandler implements RequestHandler<APIGatewayV2CustomAuthorizerEvent, Object> {

    @Override
    public Object handleRequest(APIGatewayV2CustomAuthorizerEvent input, Context context) {
        return "{\n" +
                "    \"principalId\": \"me\",\n" +
                "    \"policyDocument\": {\n" +
                "        \"Version\": \"2012-10-17\",\n" +
                "        \"Statement\": [\n" +
                "            {\n" +
                "                \"Action\": \"execute-api:Invoke\",\n" +
                "                \"Effect\": \"Allow\",\n" +
                "                \"Resource\": [\n" +
                "                    \"arn:aws:execute-api:eu-west-2:719169216310:25z8t960ei/$default/*/*\"\n" +
                "                ],\n" +
                "                \"Condition\": {}\n" +
                "            },\n" +
                "            {\n" +
                "                \"Action\": \"execute-api:Invoke\",\n" +
                "                \"Effect\": \"Deny\",\n" +
                "                \"Resource\": [],\n" +
                "                \"Condition\": {}\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }
}
