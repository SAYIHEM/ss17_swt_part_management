public abstract class Part implements Comparable<Part> {

    private String id;
    private String name;

    public Part(String id, String name) throws NullPointerException, IllegalArgumentException {

        if (id == null) throw new NullPointerException("ID cant be NULL!");
        if (name == null) throw new NullPointerException("Name cant be NULL!");

        if (id.isEmpty()) throw new IllegalArgumentException("ID cant be empty!");
        if (name.isEmpty()) throw new IllegalArgumentException("Name cant be empty!");

        this.id = id;
        this.name = name;
    }

    public String getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public int compareTo(Part part) {

        if (part.getName().equals(this.getName())) {

            return 0;

        } else {

            return -1;
        }
    }

    public boolean equals(Object o) {

        if (o instanceof Part) {

            Part part = (Part) o;

            if (this.getName().equals(part.getName())) {

                return true;

            } else {

                return false;
            }

        } else {

            return false;
        }
    }
}
