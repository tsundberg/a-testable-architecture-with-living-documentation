package se.thinkcode.todo.adapters;

public class DriverFactory {
public static TodoListDriver getDriver() {
    String seam = getSeam();

    if (isService(seam)) {
        return new InMemoryServiceDriver();
    }

    if (isDatabase(seam)) {
        return new DatabaseDriver();
    }

    throw new RuntimeException("Seam <" + seam + "> not found");
}

    private static boolean isService(String seaam) {
        return seaam.equals("service");
    }

    private static boolean isDatabase(String seam) {
        return seam.equals("database");
    }

    private static String getSeam() {
        if (System.getProperty("seam") != null) {
            return System.getProperty("seam");
        }

        return "service";
    }
}
