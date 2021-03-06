package com.example.alex.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "customerApi",
        version = "v1",
        resource = "customer",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.alex.example.com",
                ownerName = "backend.myapplication.alex.example.com",
                packagePath = ""
        )
)
public class CustomerEndpoint {

    private static final Logger logger = Logger.getLogger(CustomerEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Customer.class);
    }

    /**
     * Returns the {@link Customer} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Customer} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "customer/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Customer get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting Customer with ID: " + id);
        Customer customer = ofy().load().type(Customer.class).id(id).now();
        if (customer == null) {
            throw new NotFoundException("Could not find Customer with ID: " + id);
        }
        return customer;
    }

    /**
     * Inserts a new {@code Customer}.
     */
    @ApiMethod(
            name = "insert",
            path = "customer",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Customer insert(Customer customer) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that customer.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(customer).now();
        logger.info("Created Customer with ID: " + customer.getId());

        return ofy().load().entity(customer).now();
    }

    /**
     * Updates an existing {@code Customer}.
     *
     * @param id       the ID of the entity to be updated
     * @param customer the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Customer}
     */
    @ApiMethod(
            name = "update",
            path = "customer/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Customer update(@Named("id") Long id, Customer customer) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(customer).now();
        logger.info("Updated Customer: " + customer);
        return ofy().load().entity(customer).now();
    }

    /**
     * Deletes the specified {@code Customer}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Customer}
     */
    @ApiMethod(
            name = "remove",
            path = "customer/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(Customer.class).id(id).now();
        logger.info("Deleted Customer with ID: " + id);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "customer",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Customer> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Customer> query = ofy().load().type(Customer.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Customer> queryIterator = query.iterator();
        List<Customer> customerList = new ArrayList<Customer>(limit);
        while (queryIterator.hasNext()) {
            customerList.add(queryIterator.next());
        }
        return CollectionResponse.<Customer>builder().setItems(customerList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(Customer.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Customer with ID: " + id);
        }
    }
}