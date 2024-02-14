package mapping.dtos;

import model.ToyType;

public record ToyDTO (String name, ToyType type,double price, int quantity){
}
