package lt.vu.rest;

import lombok.*;
import lt.vu.entities.Car;
import lt.vu.entities.Dealer;
import lt.vu.persistence.DealersDAO;
import lt.vu.rest.contracts.CarDto;
import lt.vu.persistence.CarsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/cars")
public class CarsController {

    @Inject
    @Setter @Getter
    private CarsDAO carsDAO;

    @Inject
    @Setter @Getter
    private DealersDAO dealersDAO;

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(CarDto carDto) {
        Car car = new Car();
        Dealer dealer = dealersDAO.findByName(carDto.getDealerName());
        if (dealer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        car.setDealer(dealer);
        car.setMake(carDto.getMake());
        car.setModel(carDto.getModel());
        car.setPrice(carDto.getPrice());
        car.setLeasingPrice(carDto.getLeasingPrice());
        carsDAO.persist(car);
        return Response.ok().build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Car car = carsDAO.findOne(id);
        if (car == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CarDto carDto = new CarDto();
        carDto.setMake(car.getMake());
        carDto.setModel(car.getModel());
        carDto.setDealerName(car.getDealer().getName());

        return Response.ok(carDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer playerId,
            CarDto carData) {
        try {
            Car existingCar = carsDAO.findOne(playerId);
            if (existingCar == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingCar.setMake(carData.getMake());
            existingCar.setModel(carData.getModel());
            carsDAO.update(existingCar);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
