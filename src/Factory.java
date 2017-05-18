public class Factory {

    private ReceivingStock stock;
    private Purchasing purchasing;

    public Factory(Purchasing purchasing, ReceivingStock receivingStock) throws NullPointerException {

        if (purchasing == null) throw new NullPointerException("Purchasing cant be NULL!");
        if (receivingStock == null) throw new NullPointerException("ReceivingStock cant be NULL!");

        this.purchasing = purchasing;
        this.stock = receivingStock;
    }

    public ReceivingStock getStock() {

        return stock;
    }

    public Purchasing getPurchasing() {

        return purchasing;
    }

    public static Part create(PartType partType, String id, String name) throws NullPointerException, IllegalArgumentException {

        if (partType == null) throw new NullPointerException("PartType cant be NULL!");
        if (id == null) throw new NullPointerException("ID cant be NULL!");
        if (name == null) throw new NullPointerException("Name cant be NULL!");

        if (id.isEmpty()) throw new IllegalArgumentException("ID cant be empty!");
        if (name.isEmpty()) throw new IllegalArgumentException("Name cant be empty!");

        // TODO: Implement create and push into Stock...

        if (partType == PartType.COMPONENTS) return new Components(id, name);
        if (partType == PartType.RESOURCE) return new Resource(id, name);
        if (partType == PartType.SINGLE_COMPONENT) return new SingleComponent(id, name);

        // Should not be reached!
        return null;

    }
}
