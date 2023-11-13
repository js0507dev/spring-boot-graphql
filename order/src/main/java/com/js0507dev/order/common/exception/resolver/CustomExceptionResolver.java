package com.js0507dev.order.common.exception.resolver;

import com.js0507dev.order.common.exception.CustomBaseException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomExceptionResolver extends DataFetcherExceptionResolverAdapter {
  protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
    Map<String, Object> extensions = new HashMap<>();
    if (ex instanceof CustomBaseException customBaseException) {
      return this.makeGraphQLErrorByCustomException(customBaseException, env, extensions);
    } else {
      return null;
    }
  }

  private GraphQLError makeGraphQLErrorByCustomException(CustomBaseException ex, DataFetchingEnvironment env, Map<String, Object> extensions) {
    extensions.put("code", ex.getCode());
    return GraphqlErrorBuilder.newError()
        .errorType(ex.getErrorType())
        .message(ex.getMessage())
        .path(env.getExecutionStepInfo().getPath())
        .location(env.getField().getSourceLocation())
        .extensions(extensions)
        .build();
  }
}
