package br.com.avf.cqrs.product.command.api.commands;

import br.com.avf.cqrs.core.commands.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductCommand extends BaseCommand {
    private String id;
    private String name;
    private Integer quantity;
    private BigDecimal price;
}
