package br.com.avf.cqrs.product.command.api.resources;

import br.com.avf.cqrs.core.infraestructures.CommandDispatcher;
import br.com.avf.cqrs.product.command.api.protocols.ProductRequest;
import br.com.avf.cqrs.product.command.codec.Codec;
import br.com.avf.cqrs.product.commons.helper.IdGenerator;
import br.com.avf.cqrs.product.commons.protocols.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class UpdateProductResource {

    private final CommandDispatcher dispatcher;

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody ProductRequest request) {
        var command = Codec.toUpdateProductCommand(request);
        try {
            dispatcher.send(command);
            return new ResponseEntity<>(new BaseResponse("Product updated successfully! ID: "+command.getId()), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new BaseResponse("Something went wrong while updating product '"+command.getId()+"'"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
