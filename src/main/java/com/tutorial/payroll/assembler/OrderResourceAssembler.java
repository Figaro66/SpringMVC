package com.tutorial.payroll.assembler;

import com.tutorial.payroll.controller.OrderController;
import com.tutorial.payroll.enums.Status;
import com.tutorial.payroll.model.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderResourceAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

    @Override
    public EntityModel<Order> toModel(Order order) {

        EntityModel<Order> orderEntityModel = new EntityModel<>(order,
                linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all()).withRel("orders"));

        if (order.getStatus() == Status.IN_PROGRESS) {
            orderEntityModel.add(
                    linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));
            orderEntityModel.add(
                    linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete"));
        }

        return orderEntityModel;
    }
}
