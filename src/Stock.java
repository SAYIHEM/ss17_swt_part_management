import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Stock {

    public List<Observation> observers = new ArrayList<>();
    public Map<Part, Integer> parts = new TreeMap<>();

    public int get(Part part) throws NullPointerException {

        if (part == null) throw new NullPointerException("Part cant be NULL!");

        return this.parts.getOrDefault(part, -1);
    }

    public boolean set(Part part, int count) throws NullPointerException, IllegalArgumentException {

        if (part == null) throw new NullPointerException("Part cant be NULL!");
        if (count < 0 || !this.parts.containsKey(part)) return false;

        this.parts.replace(part, count);

        return true;
    }

    public void insert(Part part, int count) throws NullPointerException, IllegalArgumentException {

        if (part == null) throw new NullPointerException("Part cant be NULL!");
        if (count <= 0) throw new IllegalArgumentException("Count of parts cant be lower than 0!");

        if (this.parts.containsKey(part)) {

            int actualCount = this.parts.get(part);
            count += actualCount;
            this.parts.replace(part, count);

        } else {

            this.parts.put(part, count);
        }
    }

    public boolean delete(Part part, int count) throws NullPointerException, IllegalArgumentException {

        if (part == null) throw new NullPointerException("Part cant be NULL!");
        if (count < 0) return false;

        if (this.parts.containsKey(part)) {
            if (this.parts.get(part) >= count) {

                this.parts.replace(part, this.parts.get(part) - count);
                return true;

            } else {

                return false;
            }

        } else {

            return false;
        }
    }

    public void register(Observation observer) throws NullPointerException {

        if (observer == null) throw new NullPointerException("Observer to register cant be NULL!");

        this.observers.add(observer);
    }

    public void notify(Part part) throws NullPointerException {

        if (part == null) throw new NullPointerException("Part cant be NULL!");

        for (Observation o : this.observers) {

            o.alarm(part);
        }
    }
}
