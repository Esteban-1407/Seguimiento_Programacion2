package services;

import mapping.dtos.ToyDTO;
import mapping.mappers.ToyMapper;
import model.Toy;
import model.ToyType;
import utils.Constants;
import utils.FileUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class ToyServicelmpl implements  ToyService{

    private final List<Toy> toyStore;
    public ToyServicelmpl(){
        toyStore = FileUtils.getToys(new File(Constants.PATH_TOYS));
    }

    @Override
    public void addToy(ToyDTO toy) {
        Toy newToy = ToyMapper.mapFrom(toy);
        toyStore.add(newToy);
        FileUtils.saveToys(new File(Constants.PATH_TOYS), toyStore);
        getAllToys();

    }

    @Override
    public Map<ToyType, Integer> showByType() throws Exception {
        Map<ToyType,Integer> showByType = new TreeMap<>();
        for(Toy toy : toyStore){
            ToyType type = toy.getType();
            showByType.put(type,showByType.getOrDefault(type,0)+1);
        }
        return showByType;
    }




    @Override
    public int showTotalCountToy() {
        int count = 0;
        for (Toy toy : toyStore){
            count+= toy.getQuantity();
        }
        return  count;
    }

    @Override
    public double showTotalValueToys() {
        return toyStore.stream()
                .mapToDouble(toy -> toy.getPrice() * toy.getQuantity())
                .sum();
    }

    @Override
    public void decreaseQuantityToy(String toyName, int quantity) throws Exception {
        Toy toyToUpdate = findToyByName(toyName);
        if (toyToUpdate.getQuantity() < quantity)
            throw new Exception("Insufficient quantity");
        toyToUpdate.setQuantity(toyToUpdate.getQuantity() - quantity);
        FileUtils.saveToys(new File(Constants.PATH_TOYS), toyStore);

    }

    @Override
    public void increaseQuantityToy(String toyName, int quantity) throws Exception {
        Toy toyToUpdate = findToyByName(toyName);
        toyToUpdate.setQuantity(toyToUpdate.getQuantity() + quantity);
        FileUtils.saveToys(new File(Constants.PATH_TOYS), toyStore);
    }

    @Override
    public Object maxToy() throws Exception {
        ToyType firstKey = ((TreeMap<ToyType, Integer>) showByType()).firstKey();
        Integer firstValue = showByType().get(firstKey);
        return new AbstractMap.SimpleEntry<>(firstKey,firstValue);
    }

    @Override
    public Object minToy() throws Exception {
        ToyType lastKey = ((TreeMap<ToyType, Integer>) showByType()).lastKey();
        Integer lastValue = showByType().get(lastKey);
        return new AbstractMap.SimpleEntry<>(lastKey,lastValue);
    }






    @Override
    public List<ToyDTO> filterToysByPrice(double minPrice) {
        return toyStore.stream()
                .filter(toy -> toy.getPrice() > minPrice)
                .map(ToyMapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public List<ToyDTO> sortToysByQuantity() {
        return toyStore.stream()
                .sorted(Comparator.comparingInt(Toy::getQuantity))
                .map(ToyMapper::mapFrom)
                .collect(Collectors.toList());
    }

    private List<ToyDTO> getAllToys() {
        return toyStore.stream()
                .map(ToyMapper::mapFrom)
                .collect(Collectors.toList());
    }

    private Toy findToyByName(String toyName) throws Exception {
        return toyStore.stream()
                .filter(toy -> toy.getName().equalsIgnoreCase(toyName))
                .findFirst()
                .orElseThrow(() -> new Exception("Toy not found: " + toyName));
    }
}
