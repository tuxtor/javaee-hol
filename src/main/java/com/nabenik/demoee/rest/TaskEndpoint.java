package com.nabenik.demoee.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.nabenik.demoee.dao.TaskDao;
import com.nabenik.demoee.model.Task;

@RequestScoped
@Path("/tasks")
@Produces({ "application/json", "application/xml" })
@Consumes({ "application/json", "application/xml" })
public class TaskEndpoint {
	
	@Inject
	TaskDao taskRepository;

	@POST
	public Response create(final Task task) {
		taskRepository.create(task);
		return Response.created(UriBuilder.fromResource(TaskEndpoint.class).path(String.valueOf(task.getId())).build())
				.build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		Task task = taskRepository.findById(id);
		if (task == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(task).build();
	}

	@GET
	public List<Task> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		final List<Task> tasks = taskRepository.listAll(startPosition, maxResult);
		return tasks;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final Task task) {
		taskRepository.update(task);
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		taskRepository.deleteById(id);
		return Response.noContent().build();
	}

}
