public class ReceivingStock extends Stock {

    private int minStockItems;
    private int maxStockItems;

    public ReceivingStock(int minStockItems, int maxStockItems) throws IllegalArgumentException {

        if (minStockItems < 0) throw new IllegalArgumentException("Minimum Stock cant be lower than 0!");
        if (maxStockItems < 0) throw new IllegalArgumentException("Maximum Stock cant be lower than 0!");
        if (maxStockItems < minStockItems) throw new IllegalArgumentException("Maximum Stock cant be lower than Minimum Stock!");

        this.minStockItems = minStockItems;
        this.maxStockItems = maxStockItems;
    }

    public int getMinStockItems() {

        return minStockItems;
    }

    public int getMaxStockItems() {

        return maxStockItems;
    }

    public boolean set(Part part, int count) throws NullPointerException, IllegalArgumentException {

        boolean super_set = super.set(part, count);

        // Notify if under MinStock
        if (count < this.minStockItems) {

            this.notify(part);
        }

        return super_set;
    }

    public boolean delete(Part part, int count) throws NullPointerException, IllegalArgumentException {

        boolean super_delete = super.delete(part, count);

        // Notify if under MinStock
        if (get(part) - count < this.minStockItems) {

            this.notify(part);
        }

        return super_delete;
    }
}
