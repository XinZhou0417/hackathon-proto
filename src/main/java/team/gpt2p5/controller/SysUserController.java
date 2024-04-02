package team.gpt2p5.controller;

import team.gpt2p5.model.SysUser;
import team.gpt2p5.rest.Response;

import java.util.ArrayList;
import java.util.List;

public class SysUserController {

    public Response handleRequest() {

        List<SysUser> users = new ArrayList<>();
        users.add(new SysUser("001", "apoch", "123@matrix.com"));
        users.add(new SysUser("002", "switch", "13233@matrix.com"));
        users.add(new SysUser("003", "trinity", "1sdf3@matrix.com"));
        users.add(new SysUser("004", "neo", "neo@matrix.com"));

        return Response.success("User info successfully retrieved!", null, users);
    }

}
