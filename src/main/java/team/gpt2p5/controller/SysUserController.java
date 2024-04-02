package team.gpt2p5.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import team.gpt2p5.model.SysUser;
import team.gpt2p5.rest.HttpMethod;
import team.gpt2p5.rest.Response;

import java.util.*;

public class SysUserController implements RequestHandler<APIGatewayProxyRequestEvent, Response> {

    List<String> RESOURCES = Arrays.asList(
            "/sysUser",
            "/sysUser/{userId}",
            "/sysUser/allUsers"
    );


    @Override
    public Response handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {
        Response response = null;
        String resources = apiGatewayProxyRequestEvent.getResource();
        context.getLogger().log("Resource::: resources = " + resources);
        String method = apiGatewayProxyRequestEvent.getHttpMethod();


        try {
            if (resources.equals(RESOURCES.get(0))) {
                String body = apiGatewayProxyRequestEvent.getBody();
                SysUser newUser = new ObjectMapper().readValue(body, SysUser.class);
                response = saveNewUser(newUser);
                context.getLogger().log("Response:::POST:::created an user info by id");
            } else if (resources.equals(RESOURCES.get(1))) {
                String userId = apiGatewayProxyRequestEvent.getPathParameters().get("userId");
                if (method.equals(HttpMethod.GET)) {
                    response = getUserByID(userId);
                    context.getLogger().log("Response:::GET:::retrieved an user info by id");
                } else if (method.equals(HttpMethod.PUT)) {
                    String body = apiGatewayProxyRequestEvent.getBody();
                    context.getLogger().log("PUT:::Body: " + body);
                    SysUser user = new ObjectMapper().readValue(body, SysUser.class);
                    response = updateUser(user);
                    context.getLogger().log("Response:::PUT:::updated a newUser");
                } else {
                    context.getLogger().log("Response:::DELETE:::deleted an user by id");
                }
            } else {
                response = getAllUsers();
            }

        } catch (Exception e) {
            context.getLogger().log("Error "+ Arrays.toString(e.getStackTrace()));
        }
        return response;
    }

    public Response getAllUsers() throws JsonProcessingException {

        List<SysUser> users = new ArrayList<>();
        users.add(new SysUser("001", "apoch", "123@matrix.com"));
        users.add(new SysUser("002", "switch", "13233@matrix.com"));
        users.add(new SysUser("003", "trinity", "1sdf3@matrix.com"));
        users.add(new SysUser("004", "neo", "neo@matrix.com"));

        return Response.success("User info successfully retrieved!", null, users);
    }


    public Response getUserByID(String userId) throws JsonProcessingException {
        SysUser user = new SysUser(userId, "neo", "neo@matrix.com");
        return Response.success("User info for user#{"+userId+"} is successfully retrieved!", user, null);
    }

    public Response saveNewUser(SysUser newUser) throws JsonProcessingException {
        return Response.success("New user created!", newUser, null);
    }

    public Response updateUser(SysUser user) throws JsonProcessingException {
        return Response.success("Updated user#{"+ user.getUserId() +"}", user, null);
    }

}
