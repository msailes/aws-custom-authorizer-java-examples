package authorizers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2CustomAuthorizerEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import responses.IAMPolicyResponse;

import java.util.Arrays;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonList;

public class IAMPolicyResponseHandler implements RequestHandler<APIGatewayV2CustomAuthorizerEvent, IAMPolicyResponse>  {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public IAMPolicyResponse handleRequest(APIGatewayV2CustomAuthorizerEvent input, Context context) {
        IAMPolicyResponse iamPolicyResponse = IAMPolicyResponse.builder()
                .withPrincipalId("me")
                .withPolicyDocument(IAMPolicyResponse.PolicyDocument.builder()
                        .withStatements(Arrays.asList(
                                IAMPolicyResponse.Statement.builder()
                                        .withAction("execute-api:Invoke")
                                        .withEffect("Allow")
                                        .withResource(singletonList("arn:aws:execute-api:eu-west-2:719169216310:25z8t960ei/$default/*/*"))
                                        .withCondition(emptyMap())
                                        .build(),
                                IAMPolicyResponse.Statement.builder()
                                        .withAction("execute-api:Invoke")
                                        .withEffect("Deny")
                                        .withResource(emptyList())
                                        .withCondition(emptyMap())
                                        .build()))
                        .withVersion("2012-10-17")
                        .build())
                .build();

        try {
            context.getLogger().log(objectMapper.writeValueAsString(iamPolicyResponse));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return iamPolicyResponse;
    }
}
