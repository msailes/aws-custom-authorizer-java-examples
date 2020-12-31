package authorizers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2CustomAuthorizerEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import responses.AuthPolicy;

public class AuthPolicyHandler implements RequestHandler<APIGatewayV2CustomAuthorizerEvent, AuthPolicy> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public AuthPolicy handleRequest(APIGatewayV2CustomAuthorizerEvent input, Context context) {
        AuthPolicy authPolicy = new AuthPolicy("me", AuthPolicy.PolicyDocument.getAllowOnePolicy("eu-west-2", "719169216310", "25z8t960ei", "$default", AuthPolicy.HttpMethod.ALL, "/*"));

        try {
            context.getLogger().log(objectMapper.writeValueAsString(authPolicy));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return authPolicy;
    }
}
