package responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class IAMPolicyResponse {

    private String principalId;
    private PolicyDocument policyDocument;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> context;

    @Data
    @Builder(setterPrefix = "with")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PolicyDocument {

        @JsonProperty("Version")
        private String version;
        @JsonProperty("Statement")
        private List<Statement> statements;
    }

    @Data
    @Builder(setterPrefix = "with")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Statement {

        @JsonProperty("Action")
        private String action;
        @JsonProperty("Effect")
        private String effect;
        @JsonProperty("Resource")
        private List<String> resource;
        @JsonProperty("Condition")
        private Map<String, Object> condition;
    }
}