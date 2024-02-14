package mapping;

import mapping.dtos.ToyDTO;
import model.Toy;

public class ToyMapper {
    public static Toy mapFrom(ToyDTO dto){
        return new Toy(dto.name(),dto.type(),dto.price(),dto.quantity());
    }
    public static ToyDTO mapFrom(Toy model){
        return new ToyDTO (model.getName(),model.getQuantity(),model.getPrice(),model.getType());
    }
}
