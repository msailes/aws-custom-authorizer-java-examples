import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import responses.AuthPolicy;
import responses.IAMPolicyResponse;

import java.util.Arrays;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonList;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;

public class ComparsionTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void compareImpls() throws JsonProcessingException {
        AuthPolicy authPolicy = new AuthPolicy("me", AuthPolicy.PolicyDocument.getAllowOnePolicy("eu-west-2", "719169216310", "25z8t960ei", "$default", AuthPolicy.HttpMethod.ALL, "/*"));

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

        String iamPolicyResponseAsString = objectMapper.writeValueAsString(iamPolicyResponse);
        String authPolicyAsString = objectMapper.writeValueAsString(authPolicy);

        assertThatJson(iamPolicyResponseAsString).when(IGNORING_ARRAY_ORDER).isEqualTo(authPolicyAsString);
    }
}
