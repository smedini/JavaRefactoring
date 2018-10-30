package com.h2rd.refactoring.web;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/users")
@Consumes({ "application/json" })
@Produces({ "application/json" })
//Its better approach to use interface to make a contract with a consumer. This way even if the implementation changes, teh consumer is not affected.
// This ensures portable, extensible and easily maintainable code.
public interface UserResourceApi {

	@GET
	@Path("/add")
	Response addUser(@QueryParam("name") String name,
									 @QueryParam("email") String email,
									 @QueryParam("role") List<String> roles);

	@GET
	@Path("/update")
	Response updateUser(@QueryParam("name") String name,
											@QueryParam("email") String email,
											@QueryParam("role") List<String> roles);

	@GET
	@Path("/delete")
	Response deleteUser(@QueryParam("name") String name,
											@QueryParam("email") String email,
											@QueryParam("role") List<String> roles);

	@GET
	@Path("/path")
	Response getUsers();

	@GET
	@Path("/search")
	Response findUser(@QueryParam("name") String name);
}
