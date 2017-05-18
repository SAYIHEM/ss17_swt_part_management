public class Purchasing implements Observation {

    private ReceivingStock receivingStock;

    public Purchasing(ReceivingStock receivingStock) throws NullPointerException {

        if (receivingStock == null) throw new NullPointerException("ReceivingStock cant be NULL!");

        this.receivingStock = receivingStock;
    }


    @Override
    public void alarm(Part part) {

        int count = this.receivingStock.getMaxStockItems() - this.receivingStock.get(part);
        this.buy(part, count);
    }

    public void buy(Part part, int count) throws NullPointerException, IllegalArgumentException {

        if (part == null) throw new NullPointerException("Part to buy cant be NULL!");
        if (count < 0) throw new IllegalArgumentException("Number of parts to buy cant be lower than 0!");

        receivingStock.insert(part, count);
    }

    public ReceivingStock getStock() {

        return this.receivingStock;
    }
}
